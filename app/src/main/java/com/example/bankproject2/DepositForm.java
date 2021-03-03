package com.example.bankproject2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.Deposit;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.DepositService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
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
    int selected_account_number;
    Spinner spinner;
   // Date date = new Date();
    List<Account> allAccount= new ArrayList<>();

    List<Integer> accountNumber = new ArrayList<>();





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

        spinner = findViewById(R.id.accountNumberId);

        depositButton.setOnClickListener(this);
        depoCancelButton.setOnClickListener(this);
        depositformbackButton.setOnClickListener(this);


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
//            methodNumber.setText()
//            methodNumber.setText(values +" Number");
//            methodPin.setText(values +" Pin");



        }

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.depositButtonId){

            Deposit d = new Deposit();

            d.setAccountNumber(Integer.parseInt(spinner.getSelectedItem().toString()));
       //     d.setDepositDate(date);
            d.setMethod(methodname.getText().toString());
            d.setAmount(Double.parseDouble(depositAmount.getText().toString()));


            AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
            accountService.savedeposit(d).enqueue(new Callback<Deposit>() {


                @Override
                public void onResponse(Call<Deposit> call, Response<Deposit> response) {

                    Deposit deposit = response.body();

                    Toast.makeText(DepositForm.this,"Deposit Record Successfull",Toast.LENGTH_SHORT).show();
                    System.out.println("deposit amount--------------"+deposit.getAmount());
                }

                @Override
                public void onFailure(Call<Deposit> call, Throwable t) {
                    System.out.println("Deposit record failed-----------"+t);
                    t.printStackTrace();

                }
            });


            accountService.getAccountById(Integer.parseInt(spinner.getSelectedItem().toString())).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    Account a = response.body();
                    a.setAccountNumber(a.getAccountNumber());
                    a.setCustId(a.getCustId());
                    a.setAccountType(a.getAccountType());
                    double amount = (Double.parseDouble(depositAmount.getText().toString()));
                    double oldBalance = a.getBalance();
                    double newBalance = (oldBalance + amount);

                    a.setBalance(newBalance);
                    //    a.setOpenDate(a.getOpenDate());
                    a.setPassword(a.getPassword());
                    accountService.updateAccount(a).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {

                            Toast.makeText(DepositForm.this,response.body().getAccountNumber()+" Update Account Successfull",Toast.LENGTH_SHORT).show();
                            System.out.println("new balance--------------------"+response.body().getBalance());
                            Intent intent = new Intent(DepositForm.this, HomePage.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {

                }
            });



        }

        if (v.getId() == R.id.depositcancelButtonId) {

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

        if (v.getId() == R.id.deposifromtBeckButtonId) {
            Intent intent = new Intent(DepositForm.this, DepositMethodActivity.class);
            startActivity(intent);

        }
    }



}





//        acNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(!acNumber.getSelectedItem().toString().equals("--Choose Class--"))
//                {
//                    selected_account_number = Integer.parseInt(acNumber.getSelectedItem().toString()) ;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//
//            }
//        });
