package com.example.bankproject2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RechargeForm extends AppCompatActivity implements View.OnClickListener {

    EditText operator;
    TextView operatorName;
    ImageView mobileformbackButton;
    Button rechargeButton,rechargeCancancelButton;
    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_form);

        operator = findViewById(R.id.rechargeaccountNameId);
        operatorName = findViewById(R.id.operatormethodId);
        mobileformbackButton = findViewById(R.id.mobileformBeckButtonId);
        rechargeButton = findViewById(R.id.rechargeButtonId);
        rechargeCancancelButton = findViewById(R.id.rechargeCancelButtonId);

        rechargeButton.setOnClickListener(this);
        rechargeCancancelButton.setOnClickListener(this);
        mobileformbackButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            operator.setText(values);
            operatorName.setText(values +" Number");
            



        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.rechargeButtonId){
            Intent intent = new Intent(RechargeForm.this,HomePage.class);
            startActivity(intent);

        }
        if(v.getId() == R.id.rechargeCancelButtonId){
            alertdialogbuilder = new AlertDialog.Builder(RechargeForm.this);
            alertdialogbuilder.setTitle("Eixt Recharge");
            alertdialogbuilder.setMessage("do you want to exit recharge?");
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
        if(v.getId() == R.id.mobileformBeckButtonId){
//            Intent intent = new Intent(RechargeForm.this,MobileRechargeMethod.class);
//            startActivity(intent);
            finish();
        }
    }
}