package com.faux.workshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private static final String PREFER_NAME = "Reg";
    Button loginBtn;
    EditText txtEmail, txtPassword;
    TextView signupBtn;

    UserSession session;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSession(getApplicationContext());

        txtEmail = findViewById(R.id.email_field);
        txtPassword = findViewById(R.id.password_field);

        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);

        signupBtn = findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                String uEmail = null;
                String uPassword = null;

                if (email.trim().length() > 0 && password.trim().length() > 0) {

                    if (sharedPreferences.contains("email")) {

                        uEmail = sharedPreferences.getString("email", "");
                    }
                    if (sharedPreferences.contains("txtPassword")) {
                        uPassword = sharedPreferences.getString("txtPassword", "");
                    }

                    if (email.equals(uEmail) && password.equals(uPassword)) {

                        session.createUserLoginSession(uEmail, uPassword);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        Toast.makeText(getApplicationContext(),
                                "Successfully Logged In!",
                                Toast.LENGTH_SHORT).show();

                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Email or Password is incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter Email and Password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
