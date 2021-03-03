package com.example.edulog.apis;

import com.example.edulog.models.ProfileModel;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.models.LoginModel;
import com.example.edulog.models.ResendModel;
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

    @Multipart//here you were simply copying and pasting the above api calls, but sending multipart is a different process altogether
    @POST ("Content/uploadImage")//this is the way to upload using multipart
    Call<UploadImageModel> uploadImage(@Part MultipartBody.Part part);



}