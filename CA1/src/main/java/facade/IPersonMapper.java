package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IPersonMapper {
    
    public void addEntityManagerFactory(EntityManagerFactory emf);
    
    public Person getPerson(int id);

    public List<Person> getAllPersons();
    
    public List<Person> getPersonsByZipcode(int zipCode);

    public void addPerson(Person person);

    public void deletePerson(int id);
    
    public void editPerson(Person person);
}
