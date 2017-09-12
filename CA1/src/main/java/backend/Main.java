package backend;

import entity.Address;
import entity.CityInfo;
import entity.Company;
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

public class Main
{

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");

        Persistence.generateSchema("jpaPU", new HashMap());
        PersonMapper pm = new PersonMapper();
        CompanyMapper cm = new CompanyMapper();
        Facade facade = new Facade(emf, pm, cm);

        List<Phone> phones = new ArrayList();
        phones.add(new Phone(66666666, "The devil's number"));
        Address add = new Address("Lil-marco", new CityInfo(3230, "Græsted"), "There is no additional info");
        facade.addPerson(new Person(0, phones, add, "Satan", "Lucifer"));

        List<Phone> phones2 = new ArrayList();
        phones.add(new Phone(22332233, "Bluuup"));
        Address add2 = new Address("Lil-Banana", new CityInfo(2840, "Holte"), "Joejoe");
        facade.addPerson(new Person(1, phones2, add2, "God", "Christ"));

        List<Phone> phones3 = new ArrayList();
        phones.add(new Phone(88778877, "Google.com"));
        Address add3 = new Address("Google-street 17", new CityInfo(2880, "Lyngby"), "Brrrr");
        Company google = new Company(0, phones3, add3, "Google", "Nice google!", 99889988, 860, 1000000.00);
        
    }
}
