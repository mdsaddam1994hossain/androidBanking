package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bankproject2.adapter.UtilityAdapter;

public class UtitlityBillMethod extends AppCompatActivity {

    String [] utilityService;
    int[] utilityImage = {R.drawable.wasa,R.drawable.bapex,R.drawable.elctricity};
    ListView listView;
    ImageView utilitybackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utitlity_bill_method);

        listView = findViewById(R.id.utilitylistViewId);
        utilityService = getResources().getStringArray(R.array.utility_name);
        utilitybackButton = findViewById(R.id.utilityBeckButtonId);

        UtilityAdapter utilityAdapter = new UtilityAdapter(utilityService,utilityImage,UtitlityBillMethod.this);
        listView.setAdapter(utilityAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String methName = utilityService[position];
                Toast.makeText(getApplicationContext(),methName,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UtitlityBillMethod.this, UtilityForm.class);
                intent.putExtra("items",methName);
                startActivity(intent);
            }
        });

        utilitybackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.utilityBeckButtonId){
                    finish();
                }
            }
        });
    }
}