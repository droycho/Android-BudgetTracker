package com.epicodus.budgettracker.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/19/16.
 */
@Parcel
public class Transaction {
    String mCategory;
    Double mAmount;
    String mDate;

    public Transaction() {
    }

    public Transaction(String category, Double amount, String date) {
        this.mCategory = category;
        this.mAmount = amount;
        this.mDate = date;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public Double getAmount() {
        return mAmount;
    }

    public void setAmount(Double mAmount) {
        this.mAmount = mAmount;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }
}
