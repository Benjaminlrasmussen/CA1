package facade;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CompanyMapper implements ICompanyMapper
{

    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company getCompany(int cvr)
    {
        EntityManager em = emf.createEntityManager();

        Company found = em.find(Company.class, cvr);

        em.close();
        return found;
    }

    @Override
    public List getAllCompanies()
    {
        EntityManager em = emf.createEntityManager();

        List<Company> companies = em.createQuery("select c from Company c").getResultList();
        em.close();
        return companies;
    }

    @Override
    public List getCompaniesByZipcode(int zipCode)
    {
        EntityManager em = emf.createEntityManager();

        List<Company> companies = em.createQuery("select c from Company c").getResultList();
        for (Company c : companies)
        {
            if (c.getAddress().getCityInfo().getZipCode() != zipCode)
                companies.remove(c);
        }

        return companies;
    }

}
