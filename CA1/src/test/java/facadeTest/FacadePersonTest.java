/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTest;

import entity.Person;
import facade.IPersonMapper;
import facade.PersonMapper;
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
//
//    static EntityManagerFactory emf;
//    static IPersonMapper pm;
//    static String PU = "jpaPU"; //add test Database
//
//    @BeforeClass
//    public static void setUpClass() {
//        System.out.println("SetUp");
//        emf = Persistence.createEntityManagerFactory(PU);  //add testDatabase
//        pm = new PersonMapper(emf);
//
//        pm.addPerson(new Person("Dan", "Mark"));
//        pm.addPerson(new Person("Kaj", "Olsen"));
//        pm.addPerson(new Person("Jens", "Madsen"));
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    @Test
//    public void testGetPerson() {
//        System.out.println("getPerson");
//        Person expResult = new Person();
//        Person result = pm.getPerson(0);
//        assertEquals(expResult.getFirstName(), result.getFirstName());
//    }
//
//    @Test
//    public void testGetPersons() {
//        System.out.println("getPerson");
//        Person expResult = new Person("Dan", "Mark");
//        Person result = pm.getPerson(1);
//        assertEquals(expResult.getFirstName(), result.getFirstName());
//    }
//
//    @Test
//    public void testAddPerson() {
//        System.out.println("addPerson");
//        Person expResult = new Person("Ole", "Larsen");
//        pm.addPerson(new Person("Ole", "Larsen"));
//        assertEquals(4, pm.getAllPersons().size());
//    }
//
//    @Test
//    public void testDeletePerson() {
//        System.out.println("deletePerson");
//        pm.deletePerson(2);
//        assertEquals(2, pm.getAllPersons().size());
//    }
}
