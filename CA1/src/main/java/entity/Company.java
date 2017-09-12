/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Peter
 */
@Entity
public class Company extends InfoEntity implements Serializable
{

    private String name;
    private String description;
    private int cvr;
    private int numEmployees;
    private double marketValue;

    public Company()
    {
    }

    public Company(int id, List<Phone> phones, Address address, String name, String description, int cvr, int numEmployees, double marketValue)
    {
        super(id, phones, address);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }
    
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "Company{" + "name=" + name + ", description=" + description + ", cvr=" + cvr + ", numEmployees=" + numEmployees + ", marketValue=" + marketValue + '}';
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getCvr()
    {
        return cvr;
    }

    public void setCvr(int cvr)
    {
        this.cvr = cvr;
    }

    public int getNumEmployees()
    {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees)
    {
        this.numEmployees = numEmployees;
    }

    public double getMarketValue()
    {
        return marketValue;
    }

    public void setMarketValue(double marketValue)
    {
        this.marketValue = marketValue;
    }

}
