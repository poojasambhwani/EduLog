package com.example.edulog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateData {

    @SerializedName("state_id")
    @Expose
    public String stateId;
    @SerializedName("state_name")
    @Expose
    public String stateName;
    @SerializedName("country_id")
    @Expose
    public String countryId;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String StateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String StateId) {
        this.stateId = stateId;
    }

}