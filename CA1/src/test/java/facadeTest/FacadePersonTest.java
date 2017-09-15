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
import facade.PersonMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
    static String PU = "jpaPUtest"; //add test Database
    static Facade facade;

    @BeforeClass
    public static void setUpClass() {

        HashMap properties = new HashMap();
        properties.put("javax.persistence.schema-generation.drop-script-source", "sql/drop_test.sql");
        Persistence.generateSchema(PU, properties);

        properties.remove("javax.persistence.schema-generation.drop-script-source");
        
        
        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU,properties);  //add testDatabase
        facade = new Facade(emf, new PersonMapper(), new CompanyMapper());

        

        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3422232, "first number"));
        phones.add(new Phone(3421, "second number"));
        Address add = new Address("1. street", new CityInfo(3113, "oddense"), "mangler et n");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("svoemning", "at svoemme langt"));
        Person person = new Person("9@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
        facade.addPerson(person);
        List<Phone> phones1 = new ArrayList();
        phones.add(new Phone(0000, "first number"));
        Address add1 = new Address("Delete street", new CityInfo(0111, "hvilketsted"), "get rect");
        List<Hobby> hobbies1 = new ArrayList();
        hobbies.add(new Hobby("whatever", "whatever"));
        Person person1 = new Person("2@hotmail.com", phones, add, "salli", "harry", hobbies);
        facade.addPerson(person1);
        List<Phone> phones3 = new ArrayList();
        phones.add(new Phone(34321113, "first number"));
        phones.add(new Phone(34223321, "second number"));
        Address add3 = new Address("1. street", new CityInfo(9923, "En by"), "dont get beaten down");
        List<Hobby> hobbies3 = new ArrayList();
        hobbies.add(new Hobby("hoppe", "at hoppe hoejt"));
        Person person3 = new Person("16@hotmail.com", phones, add, "snus", "diggerydoo", hobbies);
        facade.addPerson(person3);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

//        HashMap properties = new HashMap();
//        properties.put("javax.persistence.schema-generation.drop-script-source", "sql/drop_test.sql");
//        Persistence.generateSchema(PU, properties);
//
//        properties.remove("javax.persistence.schema-generation.drop-script-source");
//        Persistence.generateSchema(PU, properties);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPerson() {

        Person result = facade.getPerson(8);

        if (result == null) {
            Assert.fail("Could not find person");
        }

        assertEquals("Ralle", result.getFirstName());
    }

    @Test
    public void testGetPersons() {

        List<Person> personArray = facade.getAllPersons();
        assertTrue(!personArray.isEmpty());
    }

    @Test
    public void testAddPerson() {

        int expResult = facade.getAllPersons().size();
        System.out.println("addPerson");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(34323344, "first number"));
        phones.add(new Phone(34211122, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("loeb", "at loebe langt"));
        Person person = new Person("1@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
        facade.addPerson(person);
        int test = facade.getAllPersons().size();

        assertEquals(expResult + 1, test);
    }

    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");

        int expResult = facade.getAllPersons().size();

        facade.deletePerson(7);
        int test = facade.getAllPersons().size();
        assertEquals(expResult-1, test);
    }

    @Test
    public void testEditPerson() {
        

        List<Phone> phones = new ArrayList();
        phones.add(new Phone(35633334, "first number"));
        Address add = new Address("edit street", new CityInfo(0002, "kktown"), "hvor");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("lave sig om", "at kunne lave sig om"));
        Person edit = new Person(5,"edit@hotmail.com", phones, add, "edit", "me", hobbies);

        facade.editPerson(edit);
        Person test = facade.getPerson(5);

        assertEquals("edit", test.getFirstName());
    }

    @Test
    public void testGetPersonByPhone() {
        

        Person test = facade.getPersonByPhoneNumber(3421);
        assertEquals("salli", test.getFirstName());
    }

}
