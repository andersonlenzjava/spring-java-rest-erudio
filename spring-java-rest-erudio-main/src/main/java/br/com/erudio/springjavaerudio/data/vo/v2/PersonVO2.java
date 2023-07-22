package br.com.erudio.springjavaerudio.data.vo.v2;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonVO2 implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String Adress;
    private String gender;
    private Date birthDay;

    public PersonVO2() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getAdress() {
        return Adress;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO2 personVO2 = (PersonVO2) o;
        return Objects.equals(id, personVO2.id) && Objects.equals(firstName, personVO2.firstName) && Objects.equals(lastName, personVO2.lastName) && Objects.equals(Adress, personVO2.Adress) && Objects.equals(gender, personVO2.gender) && Objects.equals(birthDay, personVO2.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, Adress, gender, birthDay);
    }
}
