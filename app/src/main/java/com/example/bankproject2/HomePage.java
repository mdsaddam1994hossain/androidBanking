package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

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
    }
}