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

public class UtilityForm extends AppCompatActivity implements View.OnClickListener {



    EditText methodname;
    TextView methodName;
    ImageView utilitybackButton;
    Button utilityButton,utilityCancancelButton;
    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_form);

        methodname = findViewById(R.id.utilitymethodNameId);
        methodName = findViewById(R.id.utilitymethodId);

        utilitybackButton = findViewById(R.id.utilityformBeckButtonId);
        utilityButton = findViewById(R.id.utilityButtonId);
        utilityCancancelButton = findViewById(R.id.utilityCancelButtonId);

        utilityButton.setOnClickListener(this);
        utilityCancancelButton.setOnClickListener(this);
        utilitybackButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            methodname.setText(values);
            methodName.setText(values +" Number");




        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.utilityButtonId){
            Intent intent = new Intent(UtilityForm.this,HomePage.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.utilityCancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(UtilityForm.this);
            alertdialogbuilder.setTitle("Eixt Utility");
            alertdialogbuilder.setMessage("do you want to exit utility bill ?");
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

        if(v.getId() == R.id.utilityformBeckButtonId){
            finish();
        }
    }
}