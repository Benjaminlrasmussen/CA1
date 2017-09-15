/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author Peter
 */
@Entity
public class Person extends InfoEntity implements Serializable {

    private String firstName;
    private String lastName;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Hobby> hobbies;

    public Person()
    {
    }
    public Person(String email, List<Phone> phones, Address address, String firstName, String lastName, List<Hobby> hobbies)
    {
        super(email, phones, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }
    public Person(int id, String email, List<Phone> phones, Address address, String firstName, String lastName, List<Hobby> hobbies)
    {
        super(id, email, phones, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    
}
