package com.example.bankproject2.restService;

import com.example.bankproject2.model.Deposit;
import com.example.bankproject2.model.History;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HistoryService {

    @POST("/api/history")
    Call<History> savehistory(@Body() History h);

}
