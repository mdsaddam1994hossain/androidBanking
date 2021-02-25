package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TransferForm extends AppCompatActivity {


    EditText methodname;
    TextView methodNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_form);

        methodname = findViewById(R.id.transfermethodNameId);
        methodNumber = findViewById(R.id.transfermethodId);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String  values = bundle.getString("items");
            methodname.setText(values);
            methodNumber.setText(values+ " Number");
        }
    }


}