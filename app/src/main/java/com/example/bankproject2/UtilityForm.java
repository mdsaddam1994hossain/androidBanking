package com.example.bankproject2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.History;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.HistoryService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilityForm extends AppCompatActivity implements View.OnClickListener {


    TextInputEditText methodname,utiliutyAmount,operationType,utilityAccountNumber,serviceName;
     Spinner spinner;

    List<Account> allAccount= new ArrayList<>();
    List<Integer>   accountNumber = new ArrayList<>();

    ImageView utilitybackButton;
    Button utilityButton,utilityCancancelButton;
    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility_form);

        methodname = findViewById(R.id.utilitymethodNameId);
        spinner = findViewById(R.id.utilityMyAccountNumberId);
        operationType = findViewById(R.id.utilityoperationType);
        serviceName = findViewById(R.id.utilitymethodNameId);
        utilityAccountNumber = findViewById(R.id.utilitymethodNumberId);
        utiliutyAmount = findViewById(R.id.utilityamountId);

        utilitybackButton = findViewById(R.id.utilityformBeckButtonId);
        utilityButton = findViewById(R.id.utilityButtonId);
        utilityCancancelButton = findViewById(R.id.utilityCancelButtonId);

        utilityButton.setOnClickListener(this);
        utilityCancancelButton.setOnClickListener(this);
        utilitybackButton.setOnClickListener(this);

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
        if(v.getId() == R.id.utilityButtonId){
            AccountService accountService = RestClient.getRetrofitInstance().create(AccountService.class);
            accountService.getAccountById(Integer.parseInt(spinner.getSelectedItem().toString())).enqueue(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {

                    Account a = response.body();

                    a.setAccountNumber(a.getAccountNumber());
                    a.setCustId(a.getCustId());
                    a.setAccountType(a.getAccountType());
                    double amount = (Double.parseDouble(utiliutyAmount.getText().toString()));
                    double oldBalance = a.getBalance();
                    double newBalance = oldBalance - amount;

                    a.setBalance(newBalance);
                    //    a.setOpenDate(a.getOpenDate());
                    a.setPassword(a.getPassword());

                    accountService.updateAccount(a).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            Toast.makeText(UtilityForm.this,"Recharge successfull",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UtilityForm.this,HomePage.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });

                    History h = new History();
                    h.setAccountNumber(Integer.parseInt(spinner.getSelectedItem().toString()));
                    h.setOperationType(operationType.getText().toString());
                    h.setMethod(serviceName.getText().toString());
                    //     h.setTransectionDate(date);
                    h.setAmount(Double.parseDouble(utiliutyAmount.getText().toString()));
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

        if(v.getId() == R.id.utilityCancelButtonId){

            alertdialogbuilder = new AlertDialog.Builder(UtilityForm.this);
            alertdialogbuilder.setTitle("Eixt Utility");
            alertdialogbuilder.setMessage("do you want to exit utility bill ?");
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

        if(v.getId() == R.id.utilityformBeckButtonId){
            finish();
        }
    }
}