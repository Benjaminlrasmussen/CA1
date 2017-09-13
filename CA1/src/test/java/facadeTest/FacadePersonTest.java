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

    @BeforeClass
    public static void setUpClass() {

        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);  //add testDatabase
        pm = new PersonMapper();
        cm = new CompanyMapper();
        facade = new Facade(emf, pm, cm);

        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3432, "first number"));
        phones.add(new Phone(3421, "second number"));
        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
        List<Hobby> hobbies = new ArrayList();
        hobbies.add(new Hobby("loeb", "at loebe langt"));
        Person person = new Person(10, "1@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
        facade.addPerson(person);

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPerson() {
        
//        System.out.println("getPerson " + facade.getAllPersons().size());
//        Person expResult = new Person();
//        expResult.setFirstName("Ralle");
//        Person result = pm.getPerson(3);
//        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

    @Test
    public void testGetPersons() {
//        System.out.println("getPerson");
//        Person expResult = new Person("Dan", "Mark");
//        Person result = pm.getPerson(1);
//        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

    @Test
    public void testAddPerson() {
        
//        
//        int expResult = facade.getAllPersons().size();
//        System.out.println("addPerson");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(3432, "first number"));
//        phones.add(new Phone(3421, "second number"));
//        Address add = new Address("1. street", new CityInfo(3723, "Nivaa"), "dont get beaten down");
//        List<Hobby> hobbies = new ArrayList();
//        hobbies.add(new Hobby("loeb", "at loebe langt"));
//        Person person = new Person(0, "1@hotmail.com", phones, add, "Ralle", "Rofus", hobbies);
//        facade.addPerson(person);
//        int test = facade.getAllPersons().size();
//        facade.deletePerson(2);
//        assertEquals(expResult + 1, test);
//        
    }

    @Test
    public void testDeletePerson() {
//        System.out.println("deletePerson");
//        
//        int expResult = facade.getAllPersons().size();
//        System.out.println("addPerson");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(0000, "first number"));
//        Address add = new Address("Delete street", new CityInfo(0102, "intetsted"), "get rect");
//        List<Hobby> hobbies = new ArrayList();
//        hobbies.add(new Hobby("whatever", "whatever"));
//        Person person = new Person(0, "2@hotmail.com", phones, add, "salli", "harry", hobbies);
//        facade.addPerson(person);
//        facade.deletePerson(1);
//        int test = facade.getAllPersons().size();
//        assertEquals(expResult, test);
    }
}
