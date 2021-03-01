package com.example.edulog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edulog.R;
import com.example.edulog.apis.APIClient;
import com.example.edulog.apis.ApiInterface;
import com.example.edulog.models.RegisterModel;
import com.example.edulog.utils.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText firstname,lastname, phno, email, phncode;
    private TextView tv_signIn;
    private Button btn_signup;
    ApiInterface apiInterface = new APIClient().getApiInterface();
    private JSONObject req;
    private JsonObject object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    @Override
    protected void onStart() {
        super.onStart();

        init();
        onClick();

    }

    private void init()
    {
        firstname = findViewById(R.id.et_firstname);
        lastname = findViewById(R.id.et_lastname);
        phno = findViewById(R.id.et_phnno);
        phncode = findViewById(R.id.et_phncode);
        email = findViewById(R.id.et_email);
        btn_signup = findViewById(R.id.btn_signup);
        tv_signIn = findViewById(R.id.already_signup);
        req = new JSONObject();
        validateFirstName();
        validateLastName();
        validateEmail();
        validatePhoneNo();
    }

    private Boolean validateFirstName() {
        String val = firstname.getEditableText().toString();

        if (val.isEmpty()) {
            firstname.setError("Required");
            return false;
        }
        else {
            firstname.setError(null);
            firstname.setEnabled(false);
            return true;
        }
    }

    private Boolean validateLastName() {
        String val = lastname.getEditableText().toString();

        if (val.isEmpty()) {
            lastname.setError("Required");
            return false;
        }
        else {
            lastname.setError(null);
            lastname.setEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditableText().toString();

        if (val.isEmpty()) {
            email.setError("Required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Invalid");
            return false;}
        else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = phno.getEditableText().toString();

        if (val.isEmpty()) {
            phno.setError("Required");
            return false;
        } else {
            phno.setError(null);
            phno.setEnabled(false);
            return true;
        }
    }

    /**
     * method to call signUp api
     * */
    private void onCall() {
        try {
            req.put("first_name", firstname.getText().toString().trim());
            req.put("last_name", lastname.getText().toString().trim());
            req.put("phone_code", "+91");
            req.put("phone_number", phno.getText().toString().trim());
            req.put("email", email.getText().toString().trim());

            JsonParser jsonParser = new JsonParser();
            object = (JsonObject) jsonParser.parse(req.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<RegisterModel> call = apiInterface.getRegister(object);
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                Toast.makeText(SignupActivity.this, "" + response.body().getCode(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onResponse: " + response.body().getCode() + "    " + response.body().getCode());
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Log.d("userId", "onResponse: " + response.body().getUserId());
                        SharedPreferences preferences = getSharedPreferences(Constants.USER_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(Constants.USER_ID, String.valueOf(response.body().getUserId()));
                        editor.apply();
                        Intent i = new Intent(SignupActivity.this, OtpVerification.class);
                        i.putExtra("phCode", "+91");
                        i.putExtra("phone", phno.getText().toString().trim());
                        startActivity(i);
                    } else {
                        Toast.makeText(SignupActivity.this, "Email already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);

            }
        });

    }

     private void onClick()
     {
         btn_signup.setOnClickListener(
                 new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         onCall();

                     }

                 }
         );


         tv_signIn.setOnClickListener(
                 new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                         startActivity(intent);
                     }
                 }
         );

     }
}