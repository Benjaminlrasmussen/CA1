package facade;

import entity.Hobby;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface IPersonMapper {
    
    public void addEntityManagerFactory(EntityManagerFactory emf);
    
    public Person getPerson(int id);
    
    public Person getPersonByPhoneNumber(int phoneNumber);

    public List<Person> getAllPersons();
    
    public List<Person> getPersonsByZipcode(int zipCode);
    
    public List<Person> getPersonsByHobbies(String hobbyName);

    public boolean addPerson(Person person);

    public boolean deletePerson(int id);
    
    public void editPerson(Person person);
}
