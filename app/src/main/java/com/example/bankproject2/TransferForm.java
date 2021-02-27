package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankproject2.R;

public class TransferForm extends AppCompatActivity implements View.OnClickListener {


    EditText methodname;
    TextView methodNumber;
    ImageView transferBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_form);

        methodname = findViewById(R.id.transfermethodNameId);
        methodNumber = findViewById(R.id.transfermethodId);
        transferBackButton = findViewById(R.id.transferfromtBeckButtonId);

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
        if(v.getId() == R.id.transferfromtBeckButtonId){
            Intent intent = new Intent(TransferForm.this,TransferMethodActivity.class);
            startActivity(intent);
        }
    }
}