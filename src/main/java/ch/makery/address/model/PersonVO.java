package ch.makery.address.model;

import java.time.LocalDate;
import java.util.Date;

public class PersonVO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String street;
    private Integer postalCode;
    private String city;
    private Date birthday;

    public PersonVO(Integer id, String firstName, String lastName, String street, Integer postalCode, String city, Date birthday){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
