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
import com.example.edulog.models.ResendModel;
import com.example.edulog.models.VerificationModel;
import com.example.edulog.utils.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OtpVerification extends AppCompatActivity {

    private TextView otpphn,resend;
    private EditText otpcode;
    private Button verify;
    private SharedPreferences sp;
    ApiInterface apiInterface = new APIClient().getApiInterface();
    private JSONObject req;
    private JsonObject object;
    private String token,user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

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

        otpphn = findViewById(R.id.otpphn);
        otpcode = findViewById(R.id.otpcode);
        resend = findViewById(R.id.resend);
        verify = findViewById(R.id.verify);
        req = new JSONObject();

        Intent intent1 = getIntent();
        String str1 = intent1.getStringExtra("phnno");
        otpphn.setText(str1);

        user_id = sp.getString(Constants.USER_ID, null);

    }

    /**
     * method to call LoginVerification api
     *
     * @param user_id*/
    private void onCall(String user_id)
    {
        try
        {
            req.put("user_id",user_id);
            req.put("sms_otp", otpcode.getText().toString().trim());

            JsonParser jsonParser = new JsonParser();
            object = (JsonObject) jsonParser.parse(req.toString());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<VerificationModel> call = apiInterface.getVerification(object);
        call.enqueue(new Callback<VerificationModel>()
        {
            @Override
            public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                String phn = otpphn.getText().toString().trim();

                    if (response.body().getCode() == 200)
                    {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean(Constants.LOGGED, true);
                        editor.putString(Constants.TOKEN, response.body().getData().getToken());
                        editor.apply();

                        // Log.d("token", "onResponse: "+sp.getString(Constants.USER_ID, null)+"     "+sp.getString(Constants.TOKEN, null));
                        Intent intent = new Intent(OtpVerification.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                    else {
                    Toast.makeText(OtpVerification.this, "fail", Toast.LENGTH_SHORT).show();
                    }
            }


            @Override
            public void onFailure(Call<VerificationModel> call, Throwable t)
            {
                Toast.makeText(OtpVerification.this, "" + t, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + t);

            }
        });
    }

    /**
     * method to call buttons
     * */

    private void onClick() {
        verify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            onCall(user_id);

                        }
                    }
                }
        );
    }
}

