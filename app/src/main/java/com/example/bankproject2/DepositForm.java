package com.example.bankproject2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.Deposit;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.DepositService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepositForm extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText methodname,depositAccountNumber,depositAmount;
    TextInputLayout methodNumber,methodPin;
    Button depositButton,depoCancelButton;
    ImageView depositformbackButton;
    AlertDialog.Builder alertdialogbuilder;

    List<Account> allAccount;

    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_form);

        methodname = findViewById(R.id.depositmethodNameId);
        methodNumber = findViewById(R.id.methodId);
        methodPin = findViewById(R.id.methodPin);
        depositAmount = findViewById(R.id.depositamountId);
        depositButton = findViewById(R.id.depositButtonId);
        depoCancelButton = findViewById(R.id.depositcancelButtonId);
        depositformbackButton = findViewById(R.id.deposifromtBeckButtonId);
        depositAccountNumber = findViewById(R.id.depositaccountNumberId);

        depositButton.setOnClickListener(this);
        depoCancelButton.setOnClickListener(this);
        depositformbackButton.setOnClickListener(this);

        AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
        accountService.getAllAccountByCustomerId(MainActivity.custId).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                 allAccount = response.body();

            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

                System.out.println("Error ----"+t.getMessage());
                t.printStackTrace();

            }
        });



        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String  values = bundle.getString("items");
            methodname.setText(values);
//            methodNumber.setText()
//            methodNumber.setText(values +" Number");
//            methodPin.setText(values +" Pin");



        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.depositButtonId){


            AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
            accountService.getAccountById(1).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account a = response.body();
                    double oldBalance = a.getBalance();
                    double amount = Double.parseDouble(depositAmount.getText().toString());
                    double newBalance = oldBalance + amount;
                    a.setCustId(a.getCustId());
                    a.setBalance(newBalance);
                    accountService.updateAccount(a);

                    System.out.println("oldBalance is --------"+ oldBalance);
                    System.out.println("newBalance is --------"+ newBalance);

                    Deposit d = new Deposit();
                    d.setAccountNumber(1);
                    d.setAmount(amount);
                    d.setDepositDate(date);
                    d.setMethod(methodname.getText().toString());

                    accountService.savedeposit(d);


                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    System.out.println("Error ----"+t.getMessage());
                    t.printStackTrace();
                }
            });

            Intent intent = new Intent(DepositForm.this,HomePage.class);
            startActivity(intent);
        }

        if(v.getId() == R.id.depositcancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(DepositForm.this);
            alertdialogbuilder.setTitle("Eixt deposit");
            alertdialogbuilder.setMessage("do you want to exit dposit?");
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

        if(v.getId() == R.id.deposifromtBeckButtonId){
            Intent intent = new Intent(DepositForm.this,DepositMethodActivity.class);
            startActivity(intent);

        }
    }
}
