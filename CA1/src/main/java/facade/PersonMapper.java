package facade;

import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class PersonMapper implements IPersonMapper {

    EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Person getPerson(int id)
    {
        EntityManager em = emf.createEntityManager();
        Person found = em.find(Person.class, id);
        em.close();
        return found;
    }

    @Override
    public Person getPersonByPhoneNumber(int phoneNumber)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            Person p = (Person) em.createQuery("select p from Person p Join p.phones ph where ph.number = " + phoneNumber).getResultList().get(0);
            return p;
        } catch (NoResultException e)
        {
            System.err.println(e);
            return null;
        }
    }

    @Override
    public List<Person> getAllPersons()
    {
        EntityManager em = emf.createEntityManager();
        List<Person> found = em.createQuery("select p from Person p").getResultList();
        em.close();
        return found;
    }

    @Override
    public List<Person> getPersonsByZipcode(int zipCode)
    {
        EntityManager em = emf.createEntityManager();
        List<Person> persons = em.createQuery("select p from Person p").getResultList();

        List<Person> toBeRemoved = new ArrayList();
        for (Person person : persons)
        {
            if (person.getAddress().getCityInfo().getZipCode() != zipCode)
                toBeRemoved.add(person);
        }

        for (Person person : toBeRemoved)
        {
            persons.remove(person);
        }

        em.close();
        return persons;
    }

    @Override
    public List<Person> getPersonsByHobbies(String hobbyName)
    {
        EntityManager em = emf.createEntityManager();

        List<Person> persons = em.createQuery("select p from Person p join p.hobbies h where h.name like '" + hobbyName + "'").getResultList();

        return persons;
    }

    @Override
    public boolean addPerson(Person person)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();

            CityInfo info = null;
            try
            {
                info = (CityInfo) em.createQuery("select ci from CityInfo ci where ci.zipCode = "
                        + person.getAddress().getCityInfo().getZipCode()).getSingleResult();
            } catch (NoResultException e)
            {
                System.err.println("City not found... creating it");
            }

            for (int i = 0; i < person.getHobbies().size(); i++)
            {
                try
                {
                    Hobby hobby = (Hobby) em.createQuery("select h from Hobby h where h.name like '"
                            + person.getHobbies().get(i).getName() + "'").getSingleResult();

                    // Sets the hobby to the one from db if found //
                    person.getHobbies().set(i, hobby);

                } catch (NoResultException e)
                {
                    System.err.println("Could not find hobby in database... creating it");
                }
            }

            if (info != null)
            {
                person.getAddress().setCityInfo(info);
            }

            em.persist(person);
            em.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            System.err.println(e);
            return false;
        } finally
        {
            em.close();
        }
    }

    @Override
    public boolean deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person found = em.find(Person.class, id);
        if (found != null)
        {
            em.remove(found);
            em.getTransaction().commit();
            em.close();
            return true;
        }

        em.close();
        return false;
    }

    @Override
    public void editPerson(Person person)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Person found = em.find(Person.class, person.getId());
        if (found != null)
        {
            found.setAddress(person.getAddress());
            found.setEmail(person.getEmail());
            found.setFirstName(person.getFirstName());
            found.setLastName(person.getLastName());
            found.setHobbies(person.getHobbies());
            found.setPhones(person.getPhones());
            em.getTransaction().commit();
        }

        em.close();
    }
}
