package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bankproject2.model.Customer;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.CustomerService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton,signUpButton;
    TextInputEditText emailEdit,passwordEdit;

    ArrayList customers;
    int custId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_Button_Id);
        signUpButton = findViewById(R.id.signUp_Button_Id);
        emailEdit = findViewById(R.id.emailId);
        passwordEdit = findViewById(R.id.passwoedId);

        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

        CustomerService customerService = RestClient.getRetrofitInstance().create(CustomerService.class);
        customerService.getAllCustomer().enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                customers = (ArrayList<Customer>) response.body();
                System.out.println("size of customer ----------------"+customers.size());

                if(response.body().size() > 0){
                    emailEdit.setText(response.body().get(0).getEmail());
                    passwordEdit.setText(response.body().get(0).getPassword());

                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {

                System.out.println("Error "+ t.getMessage());
                t.printStackTrace();

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_Button_Id){

            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);

        }
        if(v.getId() == R.id.signUp_Button_Id){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);

        }

    }
}