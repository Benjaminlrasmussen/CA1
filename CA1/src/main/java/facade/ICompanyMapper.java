package facade;

import entity.Company;
import java.util.List;

public interface ICompanyMapper {
    public Company getCompany(int cvr);
    public List getAllCompanies();
    public List getCompaniesByZipcode(int zipCode);
}
