package facade;

import entity.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonMapper implements IPersonMapper
{

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
        
        Person p = (Person) em.createQuery("select p from Person p Join p.phones ph where ph.number like " + phoneNumber).getResultList().get(0);
        
        return p;
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
    public void addPerson(Person person)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void deletePerson(int id)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person found = em.find(Person.class, id);
        if (found != null)
            em.remove(found);

        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void editPerson(Person person)
    {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Person found = em.find(Person.class, person.getId());
        if (found != null)
        {
            found.setId(person.getId());
            found.setEmail(person.getEmail());
            found.setAddress(person.getAddress());
            found.setPhones(person.getPhones());
            found.setFirstName(person.getFirstName());
            found.setLastName(person.getLastName());
        }

        em.getTransaction().commit();
        em.close();
    }
}
