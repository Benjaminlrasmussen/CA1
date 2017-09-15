package facade;

import entity.CityInfo;
import entity.Company;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class CompanyMapper implements ICompanyMapper {

    private EntityManagerFactory emf;

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public boolean addCompany(Company company) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            
            CityInfo info = null;
            try
            {
                info = (CityInfo) em.createQuery("select ci from CityInfo ci where ci.zipCode = "
                        + company.getAddress().getCityInfo().getZipCode()).getSingleResult();
            } catch (NoResultException e)
            {
                System.err.println("City not found... creating it");
            }
            
            if (info != null)
            {
                company.getAddress().setCityInfo(info);
            }
            
            em.persist(company);
            em.getTransaction().commit();
            return true;
        }
        catch (Exception e)
        {
            System.err.println(e);
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteCompany(int cvr) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Company found = (Company) em.createQuery("select c from Company c where c.cvr = " + cvr).getSingleResult();

        if (found != null) {
            em.remove(found);
            em.getTransaction().commit();
            em.close();
            return true;
        }

        em.close();
        return false;
    }

    @Override
    public Company getCompany(int cvr) {
        EntityManager em = emf.createEntityManager();

        Company found = (Company) em.createQuery("select c from Company c where c.cvr = " + cvr).getResultList().get(0);

        em.close();
        return found;
    }

    @Override
    public List getAllCompanies() {
        EntityManager em = emf.createEntityManager();

        List<Company> companies = em.createQuery("select c from Company c").getResultList();
        em.close();
        return companies;
    }

    @Override
    public List getCompaniesByZipcode(int zipCode) {
        EntityManager em = emf.createEntityManager();
        List<Company> companies = em.createQuery("select c from Company c").getResultList();
        List<Company> toBeRemoved = new ArrayList();
        for (Company c : companies)
        {
            if (c.getAddress().getCityInfo().getZipCode() != zipCode)
                toBeRemoved.add(c);
        }
        
        for (Company c : toBeRemoved) {
            companies.remove(c);
        }
        return companies;
    }

    @Override
    public List<Company> getCompaniesWithMoreThanXEmployees(int x) {
        EntityManager em = emf.createEntityManager();

        List<Company> companies = em.createQuery("select c from Company c where c.numEmployees >= " + x).getResultList();

        return companies;
    }
}
