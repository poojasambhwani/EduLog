package com.example.edulog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone_code")
    @Expose
    public String phoneCode;
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;
    @SerializedName("profile_pic")
    @Expose
    public String profilePic;
    @SerializedName("country_id")
    @Expose
    public String countryId;
    @SerializedName("state_id")
    @Expose
    public String stateId;
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("country_name")
    @Expose
    public String countryName;
    @SerializedName("state_name")
    @Expose
    public String stateName;
    @SerializedName("city_name")
    @Expose
    public String cityName;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;
    @SerializedName("aadhar")
    @Expose
    public String aadhar;
    @SerializedName("tenth_marksheet")
    @Expose
    public String tenthMarksheet;
    @SerializedName("twelth_marksheet")
    @Expose
    public String twelthMarksheet;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getTenthMarksheet() {
        return tenthMarksheet;
    }

    public void setTenthMarksheet(String tenthMarksheet) {
        this.tenthMarksheet = tenthMarksheet;
    }

    public String getTwelthMarksheet() {
        return twelthMarksheet;
    }

    public void setTwelthMarksheet(String twelthMarksheet) {
        this.twelthMarksheet = twelthMarksheet;
    }

}