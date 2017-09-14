package backend;

import entity.Address;
import entity.CityInfo;
import entity.Company;
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

public class Main {

    public static void main(String[] args)
    {
        populateDatabase("jpaPU", 3);
    }

    private static void populateDatabase(String puName, int iterations)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(puName);

        Persistence.generateSchema(puName, new HashMap());
        PersonMapper pm = new PersonMapper();
        CompanyMapper cm = new CompanyMapper();
        Facade facade = new Facade(emf, pm, cm);

        for (int i = 0; i < iterations; i++)
        {
            List<Phone> phones = new ArrayList();
            phones.add(new Phone(66666666, "The devil's number"));
            phones.add(new Phone(55555555, "The devil's num"));
            Address add = new Address("Lil-marco", new CityInfo(3230, "Graested"), "There is no additional info");
            List<Hobby> hobbies = new ArrayList();
            hobbies.add(new Hobby("Ged", "Leger med geder"));
            hobbies.add(new Hobby("Smoke", "Ryger halm"));
            Person satan = new Person(1, "Satan@Profanemail.66" + i, phones, add, "Satan", "Lucifer", hobbies);

            List<Phone> phones2 = new ArrayList();
            phones2.add(new Phone(22332233, "Bluuup"));
            phones2.add(new Phone(11334499, "kfgkskkfas"));
            Address add2 = new Address("Lil-Banana", new CityInfo(2840, "Holte"), "Joejoe");
            List<Hobby> hobbies2 = new ArrayList();
            hobbies2.add(new Hobby("Kage", "Bager kager"));
            hobbies2.add(new Hobby("Dart", "Kaster med dværge"));
            Person god = new Person(2, "God@Divinemail" + i + ".com", phones2, add2, "God", "Christ", hobbies2);

            facade.addPerson(satan);
            facade.addPerson(god);

            List<Phone> phones3 = new ArrayList();
            phones3.add(new Phone(88778877, "Google.com"));
            Address add3 = new Address("Google-street 17", new CityInfo(2880, "Lyngby"), "Brrrr");
            Company google = new Company(0, "Google@gmail" + i + ".com", phones3, add3, "Google", "Nice google!", 9988998 + i, 860, 1000000.00);
            facade.addCompany(google);
        }
    }
}
