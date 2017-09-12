package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class PersonMapper implements IPersonMapper
{

    EntityManagerFactory emf;

    public PersonMapper(EntityManagerFactory emf)
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
        List<Person> found = em.createQuery("select p from Person p where p.id = " + zipCode).getResultList();
        em.close();
        return found;
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
