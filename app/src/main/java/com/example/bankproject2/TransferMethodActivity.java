package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TransferMethodActivity extends AppCompatActivity {

    String[] serviceName;
    ListView listView;
    int[] methodimage ={R.drawable.bkash,R.drawable.rocket,R.drawable.nagad,R.drawable.paypal,R.drawable.webmoney};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_method);

        listView =findViewById(R.id.transferlistViewId);
        serviceName = getResources().getStringArray(R.array.transection_service);

        TransferAdapter transferAdapter = new TransferAdapter(serviceName,methodimage,getApplicationContext());
        listView.setAdapter(transferAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String methName = serviceName[position];
                Toast.makeText(getApplicationContext(),methName,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TransferMethodActivity.this,TransferForm.class);
                intent.putExtra("items",methName);
                startActivity(intent);
            }
        });
    }
}