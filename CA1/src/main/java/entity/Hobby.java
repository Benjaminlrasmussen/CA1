/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Peter
 */
@Entity
public class Hobby implements Serializable
{

    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;
    private String description;
    
    public Hobby()
    {
    }

    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public List<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }

    public String getName()
    {
        return name;
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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hobby))
        {
            return false;
        }
        Hobby other = (Hobby) object;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Hobby{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

}
