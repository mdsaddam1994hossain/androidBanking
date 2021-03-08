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


import com.example.bankproject2.R;
import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.History;
import com.example.bankproject2.model.Transfer;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.HistoryService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferForm extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText methodname,transferAmount,operationType,transfermethodNumber;

    ImageView transferBackButton;
    Button transferButton,transferCancelButton;
    AlertDialog.Builder alertdialogbuilder;

    Spinner spinner;
    List<Account> allAccount= new ArrayList<>();
    List<Integer>   accountNumber = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_form);

        methodname = findViewById(R.id.transfermethodNameId);
        transferAmount = findViewById(R.id.transferamountId);
        transferBackButton = findViewById(R.id.transferfromtBeckButtonId);
        transferButton = findViewById(R.id.transferbuttonId);
        transferCancelButton = findViewById(R.id.transferCancelbuttonId);
        operationType = findViewById(R.id.transferoperationType);
        spinner = findViewById(R.id.transferaccountNumberId);

        transferButton.setOnClickListener(this);
        transferCancelButton.setOnClickListener(this);
        transferBackButton.setOnClickListener(this);

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

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String  values = bundle.getString("items");
            methodname.setText(values);

        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.transferbuttonId){

            Transfer t = new Transfer();
            t.setFormAccountNumber(Integer.parseInt(spinner.getSelectedItem().toString()));
            //     d.setDepositDate(date);
            t.setMethod(methodname.getText().toString());
            t.setAmount(Double.parseDouble(transferAmount.getText().toString()));

            AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
            accountService.savetransfer(t).enqueue(new Callback<Transfer>() {
                @Override
                public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                    Toast.makeText(TransferForm.this,"Transfer Record Successfull",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Transfer> call, Throwable t) {

                }
            });
            accountService.getAccountById(Integer.parseInt(spinner.getSelectedItem().toString())).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account a = response.body();
                    a.setAccountNumber(a.getAccountNumber());
                    a.setCustId(a.getCustId());
                    a.setAccountType(a.getAccountType());
                    double amount = (Double.parseDouble(transferAmount.getText().toString()));
                    double oldBalance = a.getBalance();
                    double newBalance = (oldBalance - amount);
                    double charge = (amount/100) * 1.2 ;
                    double aftercharge = amount - charge;
                    a.setBalance(newBalance);
                    //    a.setOpenDate(a.getOpenDate());
                    a.setPassword(a.getPassword());

                    accountService.updateAccount(a).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Toast.makeText(TransferForm.this,"Transfer successfull",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(TransferForm.this,HomePage.class);
//                            startActivity(intent);
                            // alert dialog start

                            alertdialogbuilder = new AlertDialog.Builder(TransferForm.this);
                            alertdialogbuilder.setTitle("Confirm Transfer");
                            alertdialogbuilder.setMessage("Account Number : "+a.getAccountNumber() +"\n"
                                                            + "Amount : "+ amount +"\n"
                                                            + "charge : "+ charge +"\n"
                                                            + "Amount Transfer : " + aftercharge);
                            alertdialogbuilder.setIcon(R.drawable.dangerous_24);
                            alertdialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(TransferForm.this,HomePage.class);
                                    startActivity(intent);
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



                            // alert dialog end
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });
                    History h = new History();
                    h.setAccountNumber(Integer.parseInt(spinner.getSelectedItem().toString()));
                    h.setOperationType(operationType.getText().toString());
                    h.setMethod(methodname.getText().toString());
                    //     h.setTransectionDate(date);
                    h.setAmount(Double.parseDouble(transferAmount.getText().toString()));
                    h.setChargeAmount(charge);

                    HistoryService historyService = RestClient.getRetrofitInstance().create(HistoryService.class);
                    historyService.savehistory(h).enqueue(new Callback<History>() {
                        @Override
                        public void onResponse(Call<History> call, Response<History> response) {

                        }

                        @Override
                        public void onFailure(Call<History> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    t.printStackTrace();

                }
            });



        }



        if(v.getId() == R.id.transferCancelbuttonId){
            alertdialogbuilder = new AlertDialog.Builder(TransferForm.this);
            alertdialogbuilder.setTitle("Eixt Transfer");
            alertdialogbuilder.setMessage("do you want to exit Transfer ?");
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
        if(v.getId() == R.id.transferfromtBeckButtonId){
            Intent intent = new Intent(TransferForm.this,TransferMethodActivity.class);
            startActivity(intent);
        }
    }
}