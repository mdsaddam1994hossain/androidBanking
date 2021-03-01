package com.example.bankproject2.model;

import java.util.Date;

public class Withdraw {
    int id;
    int accountNumber;
    Date transectionDate;
    String method;
    double amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTransectionDate() {
        return transectionDate;
    }

    public void setTransectionDate(Date transectionDate) {
        this.transectionDate = transectionDate;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
