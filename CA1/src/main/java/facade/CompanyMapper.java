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
        this.emf = emf;
    }

    @Override
    public void addCompany(Company company)
    {
        EntityManager em = emf.createEntityManager();
        
        em.persist(company);
        em.close();
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
