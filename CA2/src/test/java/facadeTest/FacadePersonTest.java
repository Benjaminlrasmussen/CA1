/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTest;

import entity.Person;
import facade.FacadePerson;
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

    private EntityManagerFactory emf;
    final private FacadePerson fp;
    final private String PU = "jpaPU"; //add test Database

    public FacadePersonTest() {
        fp = new FacadePerson();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);  //add testDatabase
        fp.addEntityManagerFactory(emf);

        fp.addPerson(new Person("Dan", "Mark"));
        fp.addPerson(new Person("Kaj", "Olsen"));
        fp.addPerson(new Person("Jens", "Madsen"));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        Person expResult = new Person();
        Person result = fp.getPerson();
        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

    @Test
    public void testGetPersons() {
        System.out.println("getPerson");
        Person expResult = new Person("Dan", "Mark");
        Person result = fp.getPerson(1l);
        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person expResult = new Person("Ole", "Larsen");
        Person result = fp.addPerson(new Person("Ole", "Larsen"));
        assertEquals(4, fp.getPersons().size());
    }

    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        fp.deletePerson(2l);
        assertEquals(2, fp.getPersons().size());
    }
}
