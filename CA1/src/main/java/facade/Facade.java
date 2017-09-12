package facade;

import entity.Company;
import entity.Person;
import java.util.List;

public class Facade 
{
    private IPersonMapper personMapper;
    private ICompanyMapper companyMapper;
    
    public Facade(IPersonMapper personMapper, ICompanyMapper companyMapper)
    {
        this.personMapper = personMapper;
        this.companyMapper = companyMapper;
    }
    
    public Person getPerson(int id)
    {
        return personMapper.getPerson(id);
    }

    public List<Person> getAllPersons()
    {
        return personMapper.getAllPersons();
    }
    
    public List<Person> getPersonsByZipcode(int zipCode)
    {
        return personMapper.getPersonsByZipcode(zipCode);
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
    
    public Company getCompany(int cvr)
    {
        return companyMapper.getCompany(cvr);
    }
    
    public List getAllCompanies()
    {
        return companyMapper.getAllCompanies();
    }
    
    public List getCompaniesByZipcode(int zipCode)
    {
        return companyMapper.getCompaniesByZipcode(zipCode);
    }
}
