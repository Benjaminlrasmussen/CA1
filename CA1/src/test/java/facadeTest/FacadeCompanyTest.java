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
import static facadeTest.FacadePersonTest.PU;
import static facadeTest.FacadePersonTest.emf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FacadeCompanyTest {

    private static final String PU = "jpaPUtest";
    static private PersonMapper pm;
    static private CompanyMapper cm;
    static private Facade facade;

    public FacadeCompanyTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);  
        pm = new PersonMapper();
        cm = new CompanyMapper();
        facade = new Facade(emf, pm, cm);

        Address address = new Address();
        address.setStreet("CompanyStreet");
        Address add = new Address("Company street", new CityInfo(6655, "CPH"), "Busy street");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3310, "first number"));
        phones.add(new Phone(3330, "second number"));
        Company company = new Company(1, "Notkia@gmail.com", phones, add, "Nokia", "We make bricks", 9999, 2234, 69.0);
        facade.addCompany(company);

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println(facade.getAllCompanies().size());
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addCompany() {
        int expRes = facade.getAllCompanies().size();
        Address address = new Address();
        address.setStreet("CompanyStreet");
        Address add = new Address("Company street", new CityInfo(6655, "CPH"), "Busy street");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3313, "first number"));
        phones.add(new Phone(3321, "second number"));
        Company company = new Company(2,"Motorola@gmail.com",phones,add,"Motorola","We make stuff that doesnt work",2,130,58.2);
        facade.addCompany(company);
        
        int test = facade.getAllCompanies().size();
        
        assertEquals(expRes + 1, test);
        
    }

    @Test
    public void getCompany() {
        Address address = new Address();
        address.setStreet("CompanyStreet");
        Address add = new Address("Delete street", new CityInfo(6633, "DeleteCity"), "Deleted");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3310, "first number"));
        phones.add(new Phone(3330, "second number"));
        Company expRes = new Company(3, "deleteme@gmail.com", phones, add, "delete company", "We got deleted", 3, 2234, 69.0);
        facade.addCompany(expRes);

        Company test = facade.getCompany(3);
        assertEquals(expRes.getId(), test.getId());
        
    }
    @Test
    public void editCompany(){
//        Address address = new Address();
//        address.setStreet("CompanyStreet");
//        Address add = new Address("edit street", new CityInfo(6623, "EditeCity"), "Editme");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(3310, "first number"));
//        phones.add(new Phone(3330, "second number"));
//        Company expRes = new Company(4, "editme@gmail.com", phones, add, "edit company", "We got edited", 4, 3234, 69.5);
//        facade.addCompany(expRes);
//    
//        Address address1 = new Address();
//        address.setStreet("CompanyStreet");
//        Address add1 = new Address("edit street", new CityInfo(6623, "EditCity"), "Editme");
//        List<Phone> phones1 = new ArrayList();
//        phones.add(new Phone(3310, "first number"));
//        phones.add(new Phone(3330, "second number"));
//        Company edited = new Company(4, "editme@gmail.com", phones, add, "did u edit us", "We got edited", 4, 3234, 69.5);
//        
//        facade.e
    }
    
    @Test
    public void getCompanies(){
        List<Company> companies = facade.getAllCompanies();
        assertFalse(companies.isEmpty());
    }
    @Test
    public void deleteCompany(){
        Address address = new Address();
        address.setStreet("CompanyStreet");
        Address add = new Address("elete street", new CityInfo(6613, "eleteCity"), "eleted");
        List<Phone> phones = new ArrayList();
        phones.add(new Phone(3170, "first number"));
        phones.add(new Phone(3070, "second number"));
        Company company = new Company(5, "deleteme@gmail.com", phones, add, "doesnt exist", "aint here", 5, 2234, 38.3);
        facade.addCompany(company);
        int expRes = facade.getAllCompanies().size();
        boolean delete = facade.deleteCompany(5);
        int test = facade.getAllCompanies().size();
        System.out.println("did the company get deleted " + delete);
        assertEquals(expRes-1, test);
        
    }
    @Test
    public void getCompanyByZip(){
//        Address address = new Address();
//        address.setStreet("CompanyStreet");
//        Address add = new Address("5 vejen", new CityInfo(2820, "Charlottenlund"), "richbitch");
//        List<Phone> phones = new ArrayList();
//        phones.add(new Phone(326258170, "first number"));
//        phones.add(new Phone(30723490, "second number"));
//        Company company = new Company(6, "richierich@gmail.com", phones, add, "Money MAker", "WE rich", 6, 4, 120.02);    //companymapper get by zip virker ikke
//        facade.addCompany(company);
//        List<Company> companies = facade.getCompaniesByZipcode(2820);
//        int test = companies.size();
//        assertTrue(companies.isEmpty());
        
        
        
    }
}
