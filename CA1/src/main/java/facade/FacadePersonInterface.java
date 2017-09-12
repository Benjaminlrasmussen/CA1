/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;

/**
 *
 * @author Peter
 */
public interface FacadePersonInterface {
    
    public Person getPerson(int id);

    public List<Person> getAllPersons();
    
    public List<Person> getPersonsByZipcode(int zipCode);

    public void addPerson(Person person);

    public void deletePerson(int id);
    
    public void editPerson(Person person);
}
