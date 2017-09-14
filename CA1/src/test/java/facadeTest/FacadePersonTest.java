/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTest;

import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.CompanyMapper;
import facade.Facade;
import facade.ICompanyMapper;
import facade.IPersonMapper;
import facade.PersonMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter
 */
public class FacadePersonTest {

    static EntityManagerFactory emf;
    static IPersonMapper pm;
    static String PU = "jpaPUtest"; //add test Database
    static Facade facade;
    static ICompanyMapper cm;
    
    /*

    @BeforeClass
    public static void setUpClass() {

        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);  //add testDatabase
        pm = new PersonMapper();
        cm = new CompanyMapper();
        facade = new Facade(emf, pm, cm);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        Persistence.generateSchema(PU, new HashMap());

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPerson() {

        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3432, "first number"));
        phones.add(new Phone(3421, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("svoemning", "at svoemme langt"));
        Person person = new Person(11, "1@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
        facade.addPerson(person);

        System.out.println("getPerson " + facade.getAllPersons().size());
        Person expResult = new Person();
        expResult.setFirstName("Ralle");
        Person result = pm.getPerson(11);

        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

    @Test
    public void testGetPersons() {
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3432, "first number"));
        phones.add(new Phone(3421, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("ski", "staa paa ski"));
        Person person = new Person(12, "1@hotmail.com", phones, add, "kalle", "Fipskaeg", hobbies);
        facade.addPerson(person);
        
        
        List<Person> personArray = facade.getAllPersons();
        assertTrue(!personArray.isEmpty());
    }

    @Test
    public void testAddPerson() {

        int expResult = facade.getAllPersons().size();
        System.out.println("addPerson");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3432, "first number"));
        phones.add(new Phone(3421, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("loeb", "at loebe langt"));
        Person person = new Person(14, "1@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
        facade.addPerson(person);
        int test = facade.getAllPersons().size();

        assertEquals(expResult + 1, test);

    }

    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");

        int expResult = facade.getAllPersons().size();
        System.out.println("addPerson");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(0000, "first number"));
        Address add = new Address("Delete street", new CityInfo(0102, "intetsted"), "get rect");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("whatever", "whatever"));
        Person person = new Person(10, "2@hotmail.com", phones, add, "salli", "harry", hobbies);
        facade.addPerson(person);
        facade.deletePerson(10);
        int test = facade.getAllPersons().size();
        assertEquals(expResult, test);
    }

    @Test
    public void testEditPerson() {
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(0000, "first number"));
        Address add = new Address("Delete street", new CityInfo(0102, "intetsted"), "get rect");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("whatever", "whatever"));
        Person person = new Person(13, "2@hotmail.com", phones, add, "salli", "harry", hobbies);
        facade.addPerson(person);

        List<Phone> phones1 = new ArrayList();
        phones.add(new Phone(3564, "first number"));
        Address add1 = new Address("edit street", new CityInfo(0342, "nytsted"), "hvor");
        List<Hobby> hobbies1 = new ArrayList();
        hobbies.add(new Hobby("lave sig om", "at kunne lave sig om"));
        Person edit = new Person(13, "edit@hotmail.com", phones, add, "edit", "me", hobbies);

        facade.editPerson(edit);
        Person test = facade.getPerson(13);
        
        assertEquals(edit, test);
    }
<<<<<<< HEAD
    @Test
    public void testGetPersonByPhone(){
                List<Phone> phones = new ArrayList();
        phones.add(new Phone(34323, "first number"));
        phones.add(new Phone(342221, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("hoppe", "at hoppe hoejt"));
        Person expRes = new Person(15, "1@hotmail.com", phones, add, "snus", "diggerydoo", hobbies);
        facade.addPerson(expRes);
        
        Person test = facade.getPersonByPhoneNumber(342221);
        assertEquals(expRes, test);
    }
    
=======

    */
>>>>>>> fd2516a8d2a37f48f5f6195292f4b46682bbd7d3
}
