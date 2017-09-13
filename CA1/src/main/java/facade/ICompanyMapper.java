package facade;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface ICompanyMapper {
    public void addEntityManagerFactory(EntityManagerFactory emf);
    public boolean addCompany(Company company);
    public Company getCompany(int cvr);
    public List getAllCompanies();
    public List getCompaniesByZipcode(int zipCode);
}
