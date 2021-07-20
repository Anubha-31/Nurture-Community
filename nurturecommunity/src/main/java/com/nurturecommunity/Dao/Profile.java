package com.nurturecommunity.Dao;

import javax.mail.Multipart;
import javax.persistence.*;

@Entity
public class Profile {

    private String first_name;
    private String last_name;
    private String emailaddress;
    private String password;
    private String restaurant_name;
    private String license_number;
    private String address1;
    private String address2;
    private String city;
    private String province;
    private String country;
    private String phone;
    private String zip;
    @Id
    private int id;
    private String address;
    private String license;
//    private String restaurantName;
    private String itemName;
    private String itemDescription;
    private int numberofPackets;
    private String pickupTime;
    private String uploadedPicture;
    private String locationChange;

//    @Id
//    @Column(name="profile")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int profileId;
    public Profile(){

    }
    public Profile(String first_name, String last_name, String emailaddress, String password, String restaurant_name, String license_number, String address1, String address2, String city, String province, String country, String phone, String zip, int id, String address, String license, String restaurantName, String itemName, String itemDescription, int numberofPackets, String pickupTime, String uploadedPicture, String locationChange, int profileId) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.emailaddress = emailaddress;
        this.password = password;
        this.restaurant_name = restaurant_name;
        this.license_number = license_number;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.phone = phone;
        this.zip = zip;
        this.id = id;
        this.address = address;
        this.license = license;
//        this.restaurantName = restaurantName;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.numberofPackets = numberofPackets;
        this.pickupTime = pickupTime;
        this.uploadedPicture = uploadedPicture;
        this.locationChange = locationChange;
//        this.profileId = profileId;
    }

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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

//    public String getRestaurantName() {
//        return restaurantName;
//    }
//
//    public void setRestaurantName(String restaurantName) {
//        this.restaurantName = restaurantName;
//    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getNumberofPackets() {
        return numberofPackets;
    }

    public void setNumberofPackets(int numberofPackets) {
        this.numberofPackets = numberofPackets;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getUploadedPicture() {
        return uploadedPicture;
    }

    public void setUploadedPicture(String uploadedPicture) {
        this.uploadedPicture = uploadedPicture;
    }

    public String getLocationChange() {
        return locationChange;
    }

    public void setLocationChange(String locationChange) {
        this.locationChange = locationChange;
    }

//    public int getProfileId() {
//        return profileId;
//    }

    public void setProfileId(int profileId) {
//        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", password='" + password + '\'' +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", license_number='" + license_number + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", zip='" + zip + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", license='" + license + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", numberofPackets=" + numberofPackets +
                ", pickupTime='" + pickupTime + '\'' +
                ", uploadedPicture='" + uploadedPicture + '\'' +
                ", locationChange='" + locationChange + '\'' +
//                ", profileId=" + profileId +
                '}';
    }
}
