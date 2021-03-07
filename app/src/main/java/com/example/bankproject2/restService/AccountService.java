package com.example.bankproject2.restService;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.Deposit;
import com.example.bankproject2.model.Transfer;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AccountService {

    @GET("/api/accountsByCustId")
    Call<List<Account>> getAllAccountByCustomerId(@Query("custId") int custId);

    @GET("/api/account")
    Call<Account> getAccountById(@Query("accountNumber") int accountNumber);

    @POST("/api/createAccount")
    Call<Account> createAccount(@Body() Account a);

    @POST("/api/updateAccount")
    Call<Account> updateAccount(@Body Account a);

    @POST("/api/deposit")
    Call<Deposit> savedeposit(@Body() Deposit d);

    @POST("/api/transfer")
    Call<Transfer> savetransfer(@Body() Transfer t);




}
