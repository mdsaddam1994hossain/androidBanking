package com.example.bankproject2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DepositForm extends AppCompatActivity implements View.OnClickListener {
    EditText methodname;
    TextView methodNumber,methodPin;
    Button depositButton,depoCancelButton;
    ImageView depositformbackButton;
    AlertDialog.Builder alertdialogbuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_form);

        methodname = findViewById(R.id.depositmethodNameId);
        methodNumber = findViewById(R.id.methodId);
        methodPin = findViewById(R.id.methodPin);
        depositButton = findViewById(R.id.depositButtonId);
        depoCancelButton = findViewById(R.id.depositcancelButtonId);
        depositformbackButton = findViewById(R.id.deposifromtBeckButtonId);

        depositButton.setOnClickListener(this);
        depoCancelButton.setOnClickListener(this);
        depositformbackButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            methodname.setText(values);
            methodNumber.setText(values +" Number");
            methodPin.setText(values +" Pin");



        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.depositButtonId){
            Intent intent = new Intent(DepositForm.this,HomePage.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.depositcancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(DepositForm.this);
            alertdialogbuilder.setTitle("Eixt deposit");
            alertdialogbuilder.setMessage("do you want to exit dposit?");
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

        if(v.getId() == R.id.deposifromtBeckButtonId){
            Intent intent = new Intent(DepositForm.this,DepositMethodActivity.class);
            startActivity(intent);

        }
    }
}
