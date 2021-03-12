package com.example.edulog.apis;

import com.example.edulog.models.CityModel;
import com.example.edulog.models.CountryModel;
import com.example.edulog.models.ProfileModel;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.models.LoginModel;
import com.example.edulog.models.ResendModel;
import com.example.edulog.models.StateModel;
import com.example.edulog.models.UploadImageModel;
import com.example.edulog.models.VerificationModel;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @Multipart
    @POST ("Content/uploadImage")//this is the way to upload using multipart
    Call<UploadImageModel> uploadImage(@Part MultipartBody.Part part);

    @Headers("Content-Type: application/json")
    @POST("Profile/getCountry")
    Call<CountryModel> getCountry(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Profile/getState")
    Call<StateModel> getState(@Body JsonObject object) ;

    @Headers("Content-Type: application/json")
    @POST("Profile/getCity")
    Call<CityModel> getCity(@Body JsonObject object) ;






}