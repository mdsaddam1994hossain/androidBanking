package com.example.bankproject2.model;

import java.util.Date;

public class Transfer {

    int id;
    int toAccountNumber;
    int formAccountNumber;
    String method;
    Date transferDate;
    double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(int toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public int getFormAccountNumber() {
        return formAccountNumber;
    }

    public void setFormAccountNumber(int formAccountNumber) {
        this.formAccountNumber = formAccountNumber;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
