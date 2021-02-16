package com.example.edulog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText name, phno, email, password;
    private Button btn_signup, login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editTextTextPersonName);
        phno = findViewById(R.id.et_phno);
        email = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_login);
        login_button = findViewById(R.id.notlogin_button);

        btn_signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String et_name = name.getText().toString().trim();
                        String contact = phno.getText().toString().trim();
                        String emailid = email.getText().toString().trim();
                        String pword = password.getText().toString().trim();
                        {
                            Intent intent = new Intent(SignupActivity.this, OtpVerification.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SignupActivity.this,SigninActivity.class);
                        startActivity(intent);
                    }
                }
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}