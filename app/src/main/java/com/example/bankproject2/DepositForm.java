package com.example.bankproject2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DepositForm extends AppCompatActivity {
    EditText methodname;
    TextView methodNumber,methodPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_form);

        methodname = findViewById(R.id.depositmethodNameId);
        methodNumber = findViewById(R.id.methodId);
        methodPin = findViewById(R.id.methodPin);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            methodname.setText(values);
            methodNumber.setText(values +" Number");
            methodPin.setText(values +" Pin");



        }
    }


}
