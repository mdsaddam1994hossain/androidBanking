package com.example.bankproject2.restService;

import com.example.bankproject2.model.Account;
import com.example.bankproject2.model.Deposit;
import com.example.bankproject2.model.History;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HistoryService {

    @POST("/api/history")
    Call<History> savehistory(@Body() History h);



    @GET("/api/historyByAcnumber")
    Call<List<History>> getAllAccountByAcNumber(@Query("accountNumber") int accountNumber);


}
