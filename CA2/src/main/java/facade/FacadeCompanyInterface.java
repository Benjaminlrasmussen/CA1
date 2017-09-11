/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import java.util.List;

/**
 *
 * @author Peter
 */
public interface FacadeCompanyInterface {
    public Company getCompany(int cvr);
    public List getCompanies();
    public List getCompanies(int zipCode);
}
