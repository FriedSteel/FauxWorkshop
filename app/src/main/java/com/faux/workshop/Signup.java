package com.faux.workshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Editor editor;
    EditText txtName, txtEmail, txtPassword;
    Button signupBtn;
    TextView loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtName = findViewById(R.id.name_field);
        txtEmail = findViewById(R.id.email_field);
        txtPassword = findViewById(R.id.password_field);
        signupBtn = findViewById(R.id.signup_btn);

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
        editor = sharedPreferences.edit();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (name.trim().length() > 0 && email.trim().length() > 0 && password.trim().length() > 0) {

                    editor.putString("name", name);
                    editor.putString("email", email);
                    editor.putString("txtPassword", password);

                    editor.apply();

                    Toast.makeText(getApplicationContext(),
                            "Sign up successful! Please continue to Log in.",
                            Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Signup.this,
                            "Make sure that all details are filled!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
