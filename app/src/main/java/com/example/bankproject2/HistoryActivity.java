package com.example.bankproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.History;
import com.example.bankproject2.restClient.RestClient;
import com.example.bankproject2.restService.AccountService;
import com.example.bankproject2.restService.HistoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    Button applyButton;
    ImageView backButton;
    Spinner spinner;
    ListView listView;
    List<Account> allAccount= new ArrayList<>();
    List<Integer> accountNumber = new ArrayList<>();

    List<History> historiesByAccount = new ArrayList<>();
    List<String> date = new ArrayList<>();
    List<String> optype = new ArrayList<>();
    List<String> method = new ArrayList<>();
    List<Double> amount = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        spinner = findViewById(R.id.historyAccountNumberId);
        applyButton = findViewById(R.id.historyButtonId);
        listView = findViewById(R.id.historyListId);
        backButton = findViewById(R.id.historyBeckButtonId);

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

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                HistoryService hservice = RestClient.getRetrofitInstance().create(HistoryService.class);
                hservice.getAllAccountByAcNumber(Integer.parseInt(spinner.getSelectedItem().toString())).enqueue(new Callback<List<History>>() {
                    @Override
                    public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                        historiesByAccount = response.body();

                        for (int i=0; i<historiesByAccount.size();i++){
                            optype.add(historiesByAccount.get(i).getOperationType());
                            method.add(historiesByAccount.get(i).getMethod());
                            amount.add(historiesByAccount.get(i).getAmount());
                          //  date.add(historiesByAccount.get(i).getTransectionDate());
                        }

                        HistoryCustomAdapter historyCustomAdapter = new HistoryCustomAdapter(HistoryActivity.this,optype,method,amount);
                        listView.setAdapter(historyCustomAdapter);



                    }

                    @Override
                    public void onFailure(Call<List<History>> call, Throwable t) {

                    }
                });

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}