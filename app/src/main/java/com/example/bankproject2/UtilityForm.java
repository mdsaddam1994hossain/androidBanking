package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UtilityForm extends AppCompatActivity implements View.OnClickListener {



    EditText methodname;
    TextView methodName;
    ImageView utilitybackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_form);

        methodname = findViewById(R.id.utilitymethodNameId);
        methodName = findViewById(R.id.utilitymethodId);

        utilitybackButton = findViewById(R.id.utilityformBeckButtonId);
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
        if(v.getId() == R.id.utilityformBeckButtonId){
            finish();
        }
    }
}