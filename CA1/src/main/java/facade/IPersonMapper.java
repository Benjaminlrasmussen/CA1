package facade;

import entity.Person;
import java.util.List;

public interface IPersonMapper {
    
    public Person getPerson(int id);

    public List<Person> getAllPersons();
    
    public List<Person> getPersonsByZipcode(int zipCode);

    public void addPerson(Person person);

    public void deletePerson(int id);
    
    public void editPerson(Person person);
}
