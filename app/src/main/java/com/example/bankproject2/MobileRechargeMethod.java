package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bankproject2.adapter.RechargeCustomAdapter;

public class MobileRechargeMethod extends AppCompatActivity {

    String[] operatorName;
    ListView listView;
    ImageView mobilerechargeBackButton;
    int[] rechargeImage ={R.drawable.grameenphone,R.drawable.robi,R.drawable.telitok,R.drawable.airtel,R.drawable.banglalink};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_recharge_method);

        operatorName = getResources().getStringArray(R.array.operator_name);
        listView = findViewById(R.id.rechargelistViewId);
        mobilerechargeBackButton = findViewById(R.id.mobileBeckButtonId);

        RechargeCustomAdapter rechargeCustomAdapter = new RechargeCustomAdapter(rechargeImage,operatorName,MobileRechargeMethod.this);
        listView.setAdapter(rechargeCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String methName = operatorName[position];
                Toast.makeText(getApplicationContext(),methName,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MobileRechargeMethod.this, RechargeForm.class);
                intent.putExtra("items",methName);
                startActivity(intent);
            }
        });

        mobilerechargeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.mobileBeckButtonId){
                    Intent intent = new Intent(MobileRechargeMethod.this,HomePage.class);
                    startActivity(intent);
                }
            }
        });
    }
}