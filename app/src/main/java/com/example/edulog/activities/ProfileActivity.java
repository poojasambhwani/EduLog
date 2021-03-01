package com.example.edulog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edulog.R;
import com.example.edulog.apis.APIClient;
import com.example.edulog.apis.ApiInterface;
import com.example.edulog.models.ProfileModel;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.utils.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private EditText et_fname,et_lname,et_mail;
    ApiInterface apiInterface = new APIClient().getApiInterface();
    private JSONObject req;
    private JsonObject object;
    private String token,user_id;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        init();
        onCall(user_id,token);

    }

    private void init() {
        user_id = sp.getString(Constants.USER_ID, null);
        token = sp.getString(Constants.TOKEN, null);
        ivProfile = findViewById(R.id.ivProfile);
        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_mail = findViewById(R.id.et_mail);
        req = new JSONObject();


        Glide.with(this)
                .load("https://dev.hawkscode.com.au/edumitr/managepro/assets/uploads/profileImage/16076866071607686615664.jpg")
                .into(ivProfile);
    }

    private void onCall(String user_id,String token) {
        try {

            JSONObject array = new JSONObject();

            array.put("user_id",user_id);
            array.put("Token",token);
            req.put("auth", array);

            JsonParser jsonParser = new JsonParser();
            object = (JsonObject) jsonParser.parse(req.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ProfileModel> call = apiInterface.getProfile(object);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                try {
                    if (response.body().getCode() == 200) {
                        et_fname.setText(response.body().getData().getFirstName());
                        et_lname.setText(response.body().getData().getLastName());
                        et_mail.setText(response.body().getData().getEmail());
                    }
                }catch (NullPointerException e){}

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);

            }
        });
    }
}