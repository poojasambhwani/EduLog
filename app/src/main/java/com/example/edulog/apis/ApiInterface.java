package com.example.edulog.apis;

import com.example.edulog.models.ProfileModel;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.models.LoginModel;
import com.example.edulog.models.ResendModel;
import com.example.edulog.models.UploadImageModel;
import com.example.edulog.models.VerificationModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("Auth/Register")
    Call<RegisterModel> getRegister(@Body JsonObject object) ;


    @Headers("Content-Type: application/json")
    @POST("Auth/Login")
    Call<LoginModel> getLogin(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Auth/LoginVerificaton")
    Call<VerificationModel> getVerification(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Auth/ForgotPassword")
    Call<ResendModel> getResend(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Profile/get")
    Call<ProfileModel> getProfile(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Content/uploadImage")
    Call<UploadImageModel> uploadImage(@Body JsonObject object) ;



}