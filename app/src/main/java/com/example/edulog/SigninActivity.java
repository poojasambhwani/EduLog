package com.example.edulog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SigninActivity extends AppCompatActivity {

    private EditText et_phno, et_password;
    private Button btn_login,notlogin_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        et_phno = findViewById(R.id.et_phno);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        notlogin_button = findViewById(R.id.notlogin_button);

        btn_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = et_phno.getText().toString().trim();
                        String password = et_password.getText().toString().trim();
                        {
                            Intent intent = new Intent(SigninActivity.this, OtpVerification.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        notlogin_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
                        startActivity(intent);
                    }
                }
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}