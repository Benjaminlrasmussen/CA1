package facade;

import entity.CityInfo;
import entity.Company;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public class Facade
{

    private IPersonMapper personMapper;
    private ICompanyMapper companyMapper;

    public Facade(EntityManagerFactory emf, IPersonMapper personMapper, ICompanyMapper companyMapper)
    {
        this.personMapper = personMapper;
        this.companyMapper = companyMapper;
        this.personMapper.addEntityManagerFactory(emf);
        this.companyMapper.addEntityManagerFactory(emf);
    }

    public Person getPerson(int id)
    {
        return personMapper.getPerson(id);
    }

    public Person getPersonByPhoneNumber(int phoneNumber)
    {
        return personMapper.getPersonByPhoneNumber(phoneNumber);
    }

    public List<Person> getAllPersons()
    {
        return personMapper.getAllPersons();
    }

    public List<Person> getPersonsByZipcode(int zipCode)
    {
        return personMapper.getPersonsByZipcode(zipCode);
    }
    
    public List<Person> getPersonsByHobbies(String hobbyName)
    {
        return personMapper.getPersonsByHobbies(hobbyName);
    }

    public void addPerson(Person person)
    {
        personMapper.addPerson(person);
    }

    public void deletePerson(int id)
    {
        personMapper.deletePerson(id);
    }

    public void editPerson(Person person)
    {
        personMapper.editPerson(person);
    }

    public void addCompany(Company company)
    {
        companyMapper.addCompany(company);
    }

    public boolean deleteCompany(int cvr)
    {
        return companyMapper.deleteCompany(cvr);
    }
    
    public Company getCompany(int cvr)
    {
        return companyMapper.getCompany(cvr);
    }

    public List<Company> getAllCompanies()
    {
        return companyMapper.getAllCompanies();
    }

    public List<Company> getCompaniesByZipcode(int zipCode)
    {
        return companyMapper.getCompaniesByZipcode(zipCode);
    }
}
