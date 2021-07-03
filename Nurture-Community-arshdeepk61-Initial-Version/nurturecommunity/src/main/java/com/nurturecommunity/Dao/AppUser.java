package com.nurturecommunity.Dao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name="users")
public class AppUser {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
//    private String user_type;
    private String first_name;
    private String last_name;
    private String emailid;
    private String password;
    private String restaurant_name;
    private String license_number;
    private String address;
    private String number;
    private String zipcode;
    private String role;
    private @NotBlank boolean loggedin;

    public AppUser(){

    }

    public AppUser(Long id, String role, String first_name, String last_name, String emailid, String password, String restaurant_name, String license_number, String address, String number, String zipcode) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailid = emailid;
        this.password = password;
        this.restaurant_name=restaurant_name;
        this.license_number=license_number;
        this.address = address;
        this.number = number;
        this.zipcode = zipcode;
        this.role=role;
//        this.dob=dob;
        this.loggedin = false;

    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String getNumber() {
        return number;
    }

//    public String getDob() {
//        return dob;
//    }

//    public void setDob(String dob) {
//        this.dob = dob;
//    }

//    public String getUser_type() {
//        return user_type;
//    }

//    public void setUser_type(String user_type) {
//        this.user_type = user_type;
//    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getnNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getFull_name() {
//        return full_name;
//    }

//    public void setFull_name(String full_name) {
//        this.full_name = full_name;
//    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser)  o;
        return Objects.equals(emailid, appUser.emailid) &&
                Objects.equals(password, appUser.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, emailid, password);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", license_number='" + license_number + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
//                ", dob='" + dob + '\'' +
                ", role='" + role + '\'' +
                ", loggedin='" + loggedin + '\'' +
                +
                '}';
    }
}
