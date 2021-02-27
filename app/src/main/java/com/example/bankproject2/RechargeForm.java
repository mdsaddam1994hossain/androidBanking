package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RechargeForm extends AppCompatActivity implements View.OnClickListener {

    EditText operator;
    TextView operatorName;
    ImageView mobileformbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_form);

        operator = findViewById(R.id.rechargeaccountNameId);
        operatorName = findViewById(R.id.operatormethodId);
        mobileformbackButton = findViewById(R.id.mobileformBeckButtonId);

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
        if(v.getId() == R.id.mobileformBeckButtonId){
//            Intent intent = new Intent(RechargeForm.this,MobileRechargeMethod.class);
//            startActivity(intent);
            finish();
        }
    }
}