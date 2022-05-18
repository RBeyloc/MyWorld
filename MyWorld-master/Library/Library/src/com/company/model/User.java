package com.company.model;



import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "USER_TABLE")
public class User {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    UUID idNumber;
    String name;
    String surname;
    String birthdate;
    String address;
    String email;
    String phoneNumber;
    String status;

    public User() {
    }

    public User(String name, String surname, String birthdate, String address, String email, String phoneNumber) {
        this.idNumber = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = "enabled";
    }

    public User(String name, String surname, String birthdate, String address, String email, String phoneNumber, String status) {
        this.idNumber = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public UUID getIdNumber() {
        return idNumber;
    }

   /*    this.idNumber = idNumber;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

   /* public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }*/

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdNumber(UUID idNumber) {
        this.idNumber = idNumber;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getIdNumber(), user.getIdNumber()) && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getBirthdate(), user.getBirthdate()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(getStatus(), user.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdNumber(), getName(), getSurname(), getBirthdate(), getAddress(), getEmail(), getPhoneNumber(), getStatus());
    }

    @Override
    public String toString() {
        return "User{" +
                "idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}