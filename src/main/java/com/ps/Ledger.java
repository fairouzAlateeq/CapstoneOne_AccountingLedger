package com.ps;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Ledger {
    LocalDateTime date;
    LocalDateTime time;
    String vendor;
    float amount;

    public Ledger(LocalDateTime date, LocalDateTime time, String vendor, float amount) {
        this.date = date;
        this.time = time;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
