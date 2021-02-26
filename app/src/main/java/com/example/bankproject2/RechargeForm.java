package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RechargeForm extends AppCompatActivity {

    EditText operator;
    TextView operatorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_form);

        operator = findViewById(R.id.rechargeaccountNameId);
        operatorName = findViewById(R.id.operatormethodId);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            operator.setText(values);
            operatorName.setText(values +" Number");
            



        }
    }
}