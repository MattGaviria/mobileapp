package com.example.a1_cesar_gaviriasepulveda;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHours = findViewById(R.id.etHours);
        etRated = findViewById(R.id.etRate);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> computeAndLog());
    }

    private void computeAndLog() {
        String hoursStr = etHours.getText().toSting().trim();
        String rateStr = etRate.getText().toString().trim();

        if (TextUtils.isEmpty(hoursStr) || (TextUtils.isEmpty(rateStr))) {
            Toast.makeText(this, "Please enter hours and rate", Toast.LENGTH_SHORT).show();
            return;

        }

        double no_of_hours, hourly_rate;
        try {
            no_of_hours = Double.parseDouble(hoursStr);
            hourly_rate = Double.parseDouble(rateStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        if (no_of_hours < 0 || hourly_rate <0 ) {
            Toast.makeText(this, "Values can't be negative", Toast.LENGTH_SHORT).show();
            return;
        }


        double pay, overtimePay = 0.0;
        if (no_of_hours <= 40) {
            pay = no_of_hours * hourly_rate;
        } else {
            pay = 40 * hourly_rate;
            overtimePay = (no_of_hours - 40) * hourly_rate * 1.5; }

        double totalPay = pay + overtimePay;
        double tax = totalPay * 0.18;

        //Save payment

        Payment payment = new Payment (no_of_hours, hourly_rate, pay, overtimePay, tax);
        PaymentReposiory.add(payment);



        }

    }
}


