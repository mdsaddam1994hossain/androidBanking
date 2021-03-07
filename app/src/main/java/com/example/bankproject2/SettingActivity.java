package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bankproject2.model.Customer;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.CustomerService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {

    TextInputEditText fname,lname,phone;
    Button changeButton;

    ImageView settingbackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        fname = findViewById(R.id.fnameEditId);
        lname = findViewById(R.id.lnameEditId);
        phone = findViewById(R.id.phoneEditId);
        settingbackButton = findViewById(R.id.settingBeckButtonId);
        changeButton = findViewById(R.id.changeconfirmId);

        CustomerService customerService = RestClient.getRetrofitInstance().create(CustomerService.class);
        customerService.getCustomerById(MainActivity.custId).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer c = response.body();
                String faname = c.getFirstName();
                String laname = c.getLastName();
                String  phones = c.getPhone();

                fname.setText(faname);
                lname.setText(laname);
                phone.setText(phones);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });

//        changeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                customerService.getCustomerById(MainActivity.custId).enqueue(new Callback<Customer>() {
//                    @Override
//                    public void onResponse(Call<Customer> call, Response<Customer> response) {
//                        Customer c = response.body();
//                        c.setCustId(c.getCustId());
//                        c.setFirstName(fname.getText().toString());
//                        c.setLastName(lname.getText().toString());
//                        c.setPhone(phone.getText().toString());
//                        c.setEmail(c.getEmail());
//                        c.setPassword(c.getPassword());
//
//                        customerService.UdpateCustomer(c).enqueue(new Callback<Customer>() {
//                            @Override
//                            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                                Toast.makeText(SettingActivity.this,"Customer Update Successfull",Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onFailure(Call<Customer> call, Throwable t) {
//                                t.printStackTrace();
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<Customer> call, Throwable t) {
//                        t.printStackTrace();
//
//                    }
//                });
//
//            }
//        });

        settingbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}