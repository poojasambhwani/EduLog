package com.example.edulog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityData {

    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("city_name")
    @Expose
    public String cityName;
    @SerializedName("state_id")
    @Expose
    public String stateId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String CityName) {
        this.cityName = cityName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String StateId) {
        this.stateId = stateId;
    }

}
