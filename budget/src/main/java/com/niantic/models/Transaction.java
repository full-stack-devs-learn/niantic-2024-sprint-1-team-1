package com.niantic.models;
import java.time.LocalDate;
public class Transaction {
    private int transactionId;
    private int userId;
    private int categoryId;
    private int vendorId;
    private LocalDate transactionDate;
    private double amount;
    private String notes;

    public Transaction(){

    }
    public Transaction(int transactionId, int userId, int categoryId, int vendorId, LocalDate transactionDate, double amount, String notes){
        this.transactionId = transactionId;
        this.userId = userId;
        this. categoryId = categoryId;
        this.vendorId = vendorId;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.notes = notes;
    }
    public int getTransactionId(){
        return transactionId;
    }
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getVendorId(){
        return vendorId;
    }
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
    public int getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public LocalDate getTransactionDate(){
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
}

