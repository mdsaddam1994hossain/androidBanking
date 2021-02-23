package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    GridView gridView;
    String[] servicename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        servicename = getResources().getStringArray(R.array.service_name);
        gridView = findViewById(R.id.grid_main_id);

        CustomAdapter customAdapter = new CustomAdapter(servicename,this);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = servicename[position];

                if(item.equals("Deposit")){
                    Intent intent = new Intent(HomePage.this,DepositMethodActivity.class);
                    Toast.makeText(getApplicationContext(),"Deposit is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Transfer")){
                    Intent intent = new Intent(HomePage.this,TransferMethodActivity.class);
                    Toast.makeText(getApplicationContext(),"Transfer is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Mobile Recharge")){
                    Intent intent = new Intent(HomePage.this,MobileRechargeMethod.class);
                    Toast.makeText(getApplicationContext(),"Mobile Recharge is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Utility Bill")){
                    Intent intent = new Intent(HomePage.this,UtitlityBillMethod.class);
                    Toast.makeText(getApplicationContext(),"Utility Bill is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Request Loan")){
                    Intent intent = new Intent(HomePage.this,RequestLoanMethod.class);
                    Toast.makeText(getApplicationContext(),"Request Loan is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Request Card")){
                    Intent intent = new Intent(HomePage.this,RequestCardMethod.class);
                    Toast.makeText(getApplicationContext(),"Request Card is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("History")){
                    Intent intent = new Intent(HomePage.this,HistoryActivity.class);
                    Toast.makeText(getApplicationContext(),"History is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

                if(item.equals("Settings")){
                    Intent intent = new Intent(HomePage.this,SettingActivity.class);
                    Toast.makeText(getApplicationContext(),"Settings is clicked",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}