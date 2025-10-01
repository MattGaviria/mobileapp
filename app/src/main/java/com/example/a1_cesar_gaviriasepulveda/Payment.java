package com.example.a1_cesar_gaviriasepulveda;

public class Payment {
    public final double hours, rate, basePay, overtimePay, totalPay, tax;

    public Payment(double hours, double rate, double basePay,
                   double overtimePay, double totalPay, double tax) {
        this.hours = hours;
        this.rate = rate;
        this.basePay = basePay;
        this.overtimePay = overtimePay;
        this.totalPay = totalPay;
        this.tax = tax;
    }

    @Override
    public String toString() {
        return String.format("Hours: %.1f Rate: $%.2f Total: $%.2f Tax: $%.2f",
                hours, rate, totalPay, tax);
    }
}