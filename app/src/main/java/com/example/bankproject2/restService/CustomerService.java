package com.example.bankproject2.restService;

import com.example.bankproject2.model.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CustomerService {

    @GET("/api/customers")
    Call<List<Customer>> getAllCustomer();

    @GET("/api/customer")
    Call<Customer> getCustomerById(@Query("custId") int custId);

    @GET("/api/customerByEmail")
    Call<Customer> getCustomerByEmailAddPssword(@Query("email") String custId, @Query("password") String password);

    @POST("/api/customer")
    Call<Customer> saveCustomer(@Body Customer c);

    @POST("/api/updateCustomer")
    Call<Customer> UdpateCustomer(@Body Customer c);
}
