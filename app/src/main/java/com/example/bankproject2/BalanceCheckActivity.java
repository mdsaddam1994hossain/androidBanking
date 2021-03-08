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

import com.example.bankproject2.model.Account;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceCheckActivity extends AppCompatActivity {

    ImageView balaceBackButton;
    Spinner spinner;
    Button showButton;
    List<Account> allAccount= new ArrayList<>();
    List<Integer> accountNumber = new ArrayList<>();

    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_check);

        balaceBackButton = findViewById(R.id.balaceBeckButtonId);
        spinner = findViewById(R.id.balanceaccountNumberId);
        showButton = findViewById(R.id.balanceShowButtonId);

        AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
        accountService.getAllAccountByCustomerId(MainActivity.custId).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                allAccount = response.body();

                for (int i=0;i<allAccount.size();i++){
                    accountNumber.add(allAccount.get(i).getAccountNumber());
                    System.out.println("account number is---- "+ allAccount.get(i).getAccountNumber());
                }
                System.out.println("total account is ======="+accountNumber.size());

                ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_item,accountNumber);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {

                System.out.println("Error ----"+t.getMessage());
                t.printStackTrace();

            }
        });



        balaceBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // if click Apply button
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccountService accountService1 = RestClient.getRetrofitInstance().create(AccountService.class);
                accountService.getAccountById(Integer.parseInt(spinner.getSelectedItem().toString())).enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        Account a = response.body();
                        int anumber = a.getAccountNumber();
                        Double balance = a.getBalance();
                        String atype = a.getAccountType();
                        // start dialog
                        alertdialogbuilder = new AlertDialog.Builder(BalanceCheckActivity.this);
                        alertdialogbuilder.setTitle("Account Information");
                        alertdialogbuilder.setMessage("Account Number : "+ anumber +"\n"
                                +" Balance:  "+ balance+"\n"
                                + "Account Type : "+ atype );

                        alertdialogbuilder.setIcon(R.drawable.dangerous_24);
                        alertdialogbuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Intent intent = new Intent(BalanceCheckActivity.this,BalanceCheckActivity.class);
//                                startActivity(intent);
                            }
                        });

//                        alertdialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
//                        alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                            }
//                        });
                        AlertDialog alertDialog = alertdialogbuilder.create();
                        alertDialog.show();

                        // end dialog

                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {

                    }
                });

            }
        });
    }
}