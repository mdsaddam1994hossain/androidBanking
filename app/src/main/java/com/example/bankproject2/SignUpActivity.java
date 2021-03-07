package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bankproject2.model.Customer;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.CustomerService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText fname,lname,phone,email,password;

    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname = findViewById(R.id.fname_id);
        lname = findViewById(R.id.lname_id);
        phone = findViewById(R.id.phone_id);
        email = findViewById(R.id.email_id);
        password = findViewById(R.id.signUp_pass_id);

        signUpButton = findViewById(R.id.signUp_Button_Id);
        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Customer c = new Customer();
        c.setFirstName(fname.getText().toString());
        c.setLastName(lname.getText().toString());
        c.setPhone(phone.getText().toString());
        c.setEmail(email.getText().toString());
        c.setPassword(password.getText().toString());

        CustomerService customerService = RestClient.getRetrofitInstance().create(CustomerService.class);
        customerService.saveCustomer(c).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Toast.makeText(SignUpActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });


    }
}