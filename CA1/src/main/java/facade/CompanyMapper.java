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
    public boolean addCompany(Company company)
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        } finally
        {
            em.close();
        }
    }

    @Override
    public boolean deleteCompany(int cvr)
    {
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Company found = (Company)em.createQuery("select c from Company c where c.cvr = " + cvr).getSingleResult();
        
        if (found != null)
        {
            em.remove(found);
            em.close();
            return true;
        }
        
        em.close();
        return false;
    }

    @Override
    public Company getCompany(int cvr)
    {
        EntityManager em = emf.createEntityManager();

        Company found = (Company) em.createQuery("select c from Company c where c.cvr = " + cvr).getSingleResult();

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

    @Override
    public List<Company> getCompaniesWithMoreThanXEmployees(int x)
    {
        EntityManager em = emf.createEntityManager();
        
        List<Company> companies = em.createQuery("select c from Company c where c.numEmployees > " + x).getResultList();
        
        return companies;
    }
}
