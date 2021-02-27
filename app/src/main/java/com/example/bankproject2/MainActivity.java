package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton,signUpButton;
    TextInputEditText emailEdit,passwordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_Button_Id);
        signUpButton = findViewById(R.id.signUp_Button_Id);
        emailEdit = findViewById(R.id.emailId);
        passwordEdit = findViewById(R.id.passwoedId);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_Button_Id){
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);

        }
        if(v.getId() == R.id.signUp_Button_Id){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);

        }

    }
}