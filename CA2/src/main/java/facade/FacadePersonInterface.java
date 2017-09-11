/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Peter
 */
public interface FacadePersonInterface {

    public void addEntityManagerFactory(EntityManagerFactory emf);

    public EntityManager getEntityManager();

    public Person getPerson(int id);

    public List<Person> getPersons();
    
    
    public List<Person> getPersons(int zipCode);

    public void addPerson();

    public void deletePerson();
    
    public void editPerson();
}
