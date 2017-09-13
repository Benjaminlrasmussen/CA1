/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTest;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Phone;
import facade.CompanyMapper;
import facade.Facade;
import facade.PersonMapper;
import static facadeTest.FacadePersonTest.emf;
import java.util.ArrayList;
import java.util.List;
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
public class FacadeCompanyTest {

    static private String PU = "jpaPUtest";
    static private PersonMapper pm;
    static private CompanyMapper cm;
    static private Facade facade;

    public FacadeCompanyTest() {

    }

    @BeforeClass
    public static void setUpClass() {
//        System.out.println("SetUp");
//        emf = Persistence.createEntityManagerFactory(PU);  //add testDatabase
//        pm = new PersonMapper();
//        cm = new CompanyMapper();
//        facade = new Facade(emf, pm, cm);
//
//        Address address = new Address();
//        address.setStreet("CompanyStreet");
//        Address add = new Address("Company street", new CityInfo(6655, "CPH"), "Busy street");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(3310, "first number"));
//        phones.add(new Phone(3330, "second number"));
//        Company company = new Company(0, "Notkia@gmail.com", phones, add, "Nokia", "We make bricks", 9999, 2234, 69.0);
//        facade.addCompany(company);

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
    public void addCompany() {
//        int expRes = facade.getAllCompanies().size();
//        Address address = new Address();
//        address.setStreet("CompanyStreet");
//        Address add = new Address("Company street", new CityInfo(6655, "CPH"), "Busy street");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(3313, "first number"));
//        phones.add(new Phone(3321, "second number"));
//        Company company = new Company(0,"Motorola@gmail.com",phones,add,"Motorola","We make bricks",9999,130,58.2);
//        facade.addCompany(company);
//        
//        int test = facade.getAllCompanies().size();
//        
//        assertEquals(expRes + 1, test);
    }

    @Test
    public void getCompany() {
//        Address address = new Address();
//        address.setStreet("CompanyStreet");
//        Address add = new Address("Delete street", new CityInfo(6633, "DeleteCity"), "Deleted");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(3310, "first number"));
//        phones.add(new Phone(3330, "second number"));
//        Company expRes = new Company(0, "deleteme@gmail.com", phones, add, "delete company", "We got deleted", 3, 2234, 69.0);
//        facade.addCompany(expRes);
//
//        Company test = facade.getCompany(2);
//        assertEquals(expRes.getId(), test.getId());
//        
    }
}
