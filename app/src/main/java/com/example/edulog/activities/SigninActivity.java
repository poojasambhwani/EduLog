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

public class SigninActivity extends AppCompatActivity {

    private EditText et_phno,et_phncode;
    private TextView notlogin_button;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        et_phno = findViewById(R.id.et_phno);
        et_phncode = findViewById(R.id.et_phncode);
        btn_login = findViewById(R.id.btn_login);
        notlogin_button = findViewById(R.id.notlogin_button);

        btn_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String phno = et_phno.getText().toString().trim();
                        String code = et_phncode.getText().toString().trim();
                        {
                            Intent intent = new Intent(SigninActivity.this, OtpVerification.class);
                            startActivity(intent);
                        }
                    }
                }
        );

        try {
            notlogin_button.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                            startActivity(intent);
                        }
                    }
            );
        }catch (NullPointerException e){}

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }
}