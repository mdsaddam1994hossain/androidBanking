package com.example.bankproject2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.Customer;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.CustomerService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText customerId,balance,password;
    ImageView newAccountbackButton;
    Button newAccountButton,newAccountCancancelButton;
    AlertDialog.Builder alertdialogbuilder;

    Spinner spinner;
    List<String> accountType = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        customerId = findViewById(R.id.custIdforNewAccount);
        balance = findViewById(R.id.balanceIdforNewAccount);
        password = findViewById(R.id.passwoedIdforNewAccount);

        newAccountButton = findViewById(R.id.newAccountButtonId);
        newAccountCancancelButton = findViewById(R.id.newAccountCancelButtonId);
        newAccountbackButton = findViewById(R.id.newAccountBeckButtonId);
        spinner = findViewById(R.id.accountTypeId);

        newAccountButton.setOnClickListener(this);
        newAccountCancancelButton.setOnClickListener(this);
        newAccountbackButton.setOnClickListener(this);

        accountType.add(0,"current Account");
        accountType.add(1,"saving Account");
        accountType.add(2,"fixed Account");

        System.out.println("account type size-------"+ accountType.size());


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,accountType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.newAccountButtonId){

            Account a = new Account();
            a.setCustId(MainActivity.custId);
            a.setAccountType(spinner.getSelectedItem().toString());
            a.setBalance(Double.parseDouble(balance.getText().toString()));
            a.setPassword(password.getText().toString());

            AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
            accountService.createAccount(a).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Toast.makeText(NewAccountActivity.this,"Account create successfull",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewAccountActivity.this,HomePage.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    t.printStackTrace();

                }
            });



        }

        if(v.getId() == R.id.newAccountCancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(NewAccountActivity.this);
            alertdialogbuilder.setTitle("Eixt New Account");
            alertdialogbuilder.setMessage("do you want to exit new account?");
            alertdialogbuilder.setIcon(R.drawable.dangerous_24);
            alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = alertdialogbuilder.create();
            alertDialog.show();

        }

        if(v.getId() == R.id.newAccountBeckButtonId){
           finish();

        }
    }
}
