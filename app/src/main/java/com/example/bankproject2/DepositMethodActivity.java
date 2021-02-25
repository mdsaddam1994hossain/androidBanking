package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DepositMethodActivity extends AppCompatActivity  {

    String[] serviceName;
    ListView listView;
    int[] methodimage ={R.drawable.bkash,R.drawable.rocket,R.drawable.nagad,R.drawable.paypal,R.drawable.webmoney};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_method);

        listView = findViewById(R.id.depositlistViewId);
        serviceName = getResources().getStringArray(R.array.transection_service);

        DepositAdapter depositAdapter = new DepositAdapter(serviceName,methodimage,getApplicationContext());
            listView.setAdapter(depositAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String methName = serviceName[position];
                    Toast.makeText(getApplicationContext(),methName,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DepositMethodActivity.this,DepositForm.class);
                    intent.putExtra("items",methName);
                    startActivity(intent);
                }
            });
    }


}