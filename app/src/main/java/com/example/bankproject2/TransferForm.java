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

import com.example.bankproject2.R;

public class TransferForm extends AppCompatActivity implements View.OnClickListener {


    EditText methodname;
    TextView methodNumber;
    ImageView transferBackButton;
    Button transferButton,transferCancelButton;
    AlertDialog.Builder alertdialogbuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_form);

        methodname = findViewById(R.id.transfermethodNameId);
        methodNumber = findViewById(R.id.transfermethodId);
        transferBackButton = findViewById(R.id.transferfromtBeckButtonId);
        transferButton = findViewById(R.id.transferbuttonId);
        transferCancelButton = findViewById(R.id.transferCancelbuttonId);

        transferButton.setOnClickListener(this);
        transferCancelButton.setOnClickListener(this);
        transferBackButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String  values = bundle.getString("items");
            methodname.setText(values);
            methodNumber.setText(values+ " Number");
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.transferbuttonId){
            Intent intent = new Intent(TransferForm.this,HomePage.class);
           startActivity(intent);
        }
        if(v.getId() == R.id.transferCancelbuttonId){
            alertdialogbuilder = new AlertDialog.Builder(TransferForm.this);
            alertdialogbuilder.setTitle("Eixt Transfer");
            alertdialogbuilder.setMessage("do you want to exit Transfer ?");
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
        if(v.getId() == R.id.transferfromtBeckButtonId){
            Intent intent = new Intent(TransferForm.this,TransferMethodActivity.class);
            startActivity(intent);
        }
    }
}