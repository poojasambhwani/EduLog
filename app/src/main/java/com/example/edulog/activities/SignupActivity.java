package com.example.edulog.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edulog.R;

public class SignupActivity extends AppCompatActivity {

    private EditText name, phno, email;
    private TextView login_button;
    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editTextTextPersonName);
        phno = findViewById(R.id.et_phno);
        email = findViewById(R.id.editTextTextPersonName2);
        btn_signup = findViewById(R.id.btn_login);
        login_button = findViewById(R.id.notlogin_tv);

        btn_signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String et_name = name.getText().toString().trim();
                        String contact = phno.getText().toString().trim();
                        String emailid = email.getText().toString().trim();
                        {
                            Intent intent = new Intent(SignupActivity.this, OtpVerification.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        try {
            login_button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                            startActivity(intent);
                        }
                    }
            );
        }catch (NullPointerException e){}


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}