package facade;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManagerFactory;

public interface ICompanyMapper {
    public void addEntityManagerFactory(EntityManagerFactory emf);
    public boolean addCompany(Company company);
    public boolean deleteCompany(int cvr);
    public Company getCompany(int cvr);
    public List<Company> getAllCompanies();
    public List<Company> getCompaniesByZipcode(int zipCode);
}
