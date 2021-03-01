package com.example.edulog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edulog.R;
import com.example.edulog.apis.APIClient;
import com.example.edulog.apis.ApiInterface;
import com.example.edulog.models.LoginModel;
import com.example.edulog.utils.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    private EditText et_phno,et_phncode;
    private TextView stmt_signup;
    private Button btn_login;
    private SharedPreferences sp;
    ApiInterface apiInterface = new APIClient().getApiInterface();
    private JSONObject req;
    private JsonObject object;
    private String token,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sp = getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    @Override
    protected void onStart() {
        super.onStart();

        init();
        onClick();

    }

    private void init() {

        et_phno = findViewById(R.id.et_phnno);
        et_phncode = findViewById(R.id.et_phncode);
        btn_login = findViewById(R.id.btn_signin);
        stmt_signup = findViewById(R.id.stmt_signup);
        req = new JSONObject();
        validatePhoneNo();

        token = String.valueOf(sp.getString(Constants.TOKEN,null));

    }

    private Boolean validatePhoneNo() {
        String val = et_phno.getEditableText().toString();

        if (val.isEmpty()) {
            et_phno.setError("Field cannot be empty");
            return false;
        } else {
            et_phno.setError(null);
            et_phno.setEnabled(false);
            return true;
        }
    }

    private void onCall(String token)
    {
        try
        {
            req.put("phone_code", et_phncode.getText().toString().trim());
            req.put("phone_number",et_phno.getText().toString().trim());
            req.put("token",token);

            JsonParser jsonParser = new JsonParser();
            object = (JsonObject) jsonParser.parse(req.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<LoginModel> call = apiInterface.getLogin(object);
        call.enqueue(new Callback<LoginModel>()
        {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Toast.makeText(SigninActivity.this, "" + response.body().getCode(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onResponse: " + response.body().getCode() + "    " + response.body().getCode());
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Log.d("token", "onResponse: " + response.body().getCode());
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(Constants.LOGGED,true);
                        editor.putString(Constants.USER_ID, String.valueOf(response.body().getData().getUserId()));
                        editor.apply();
                        Intent i = new Intent(SigninActivity.this, OtpVerification.class);
                        i.putExtra("phCode", "+91");
                        i.putExtra("phone", et_phno.getText().toString().trim());
                        startActivity(i);
                    } else {
                        Toast.makeText(SigninActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                        Toast.makeText(SigninActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t)
            {
                Toast.makeText(SigninActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: "+t);
            }
        });
    }


    private void onClick()
    {
            btn_login.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                onCall(token);
                        }
                    }
            );

            stmt_signup.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                            startActivity(intent);
                        }
                    }
            );
    }

}