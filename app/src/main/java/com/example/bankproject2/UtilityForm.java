package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UtilityForm extends AppCompatActivity {

    EditText methodname;
    TextView methodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_form);

        methodname = findViewById(R.id.utilitymethodNameId);
        methodName = findViewById(R.id.utilitymethodId);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            methodname.setText(values);
            methodName.setText(values +" Number");




        }
    }
}