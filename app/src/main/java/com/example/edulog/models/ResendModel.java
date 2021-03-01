package com.example.edulog.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResendModel {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("data")
    @Expose
    public ResendData data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResendData getData() {
        return data;
    }

    public void setData(ResendData data) {
        this.data = data;
    }

}