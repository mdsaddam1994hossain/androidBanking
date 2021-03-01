package com.example.bankproject2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView newAccountbackButton;
    Button newAccountButton,newAccountCancancelButton;
    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);


        newAccountButton = findViewById(R.id.newAccountButtonId);
        newAccountCancancelButton = findViewById(R.id.newAccountCancelButtonId);
        newAccountbackButton = findViewById(R.id.newAccountBeckButtonId);


        newAccountButton.setOnClickListener(this);
        newAccountCancancelButton.setOnClickListener(this);
        newAccountbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.newAccountButtonId){
            Intent intent = new Intent(NewAccountActivity.this,HomePage.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.newAccountCancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(NewAccountActivity.this);
            alertdialogbuilder.setTitle("Eixt New Account");
            alertdialogbuilder.setMessage("do you want to exit new account?");
            alertdialogbuilder.setIcon(R.drawable.dangerous_24);
            alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertdialogbuilder.create();
            alertDialog.show();

        }

        if(v.getId() == R.id.newAccountBeckButtonId){
           finish();

        }
    }
}
