package facade;

public class Facade 
{
    private PersonMapperInterface personMapper;
    private CompanyMapperInterface companyMapper;
    
    public Facade(PersonMapperInterface personMapper, CompanyMapperInterface companyMapper)
    {
        this.personMapper = personMapper;
        this.companyMapper = companyMapper;
    }
}
