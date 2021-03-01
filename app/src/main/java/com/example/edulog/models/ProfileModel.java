package com.example.edulog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("data")
    @Expose
    public ProfileData data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ProfileData getData() {
        return data;
    }

    public void setData(ProfileData data) {
        this.data = data;
    }

}