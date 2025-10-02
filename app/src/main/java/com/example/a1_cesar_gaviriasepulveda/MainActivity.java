package com.example.a1_cesar_gaviriasepulveda;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    private EditText etHours, etRate;
    private Button btnCalculate, btnViewLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements to variables
        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnViewLogs = findViewById(R.id.btnViewLogs);

        // Button click for calculation
        btnCalculate.setOnClickListener(v -> computeAndLog());

        // Button click to view logs
        btnViewLogs.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
        });
    }

    private void computeAndLog() {
        String hoursStr = etHours.getText().toString().trim();
        String rateStr = etRate.getText().toString().trim();

        if (TextUtils.isEmpty(hoursStr) || TextUtils.isEmpty(rateStr)) {
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

        if (no_of_hours < 0 || hourly_rate < 0) {
            Toast.makeText(this, "Values can't be negative", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculation
        double basePay, overtimePay = 0.0;
        if (no_of_hours <= 40) {
            basePay = no_of_hours * hourly_rate;
        } else {
            basePay = 40 * hourly_rate;
            overtimePay = (no_of_hours - 40) * hourly_rate * 1.5;
        }

        double totalPay = basePay + overtimePay;
        double tax = totalPay * 0.18;

        // Save payment
        Payment payment = new Payment(no_of_hours, hourly_rate, basePay, overtimePay, totalPay, tax);
        PaymentRepository.add(payment);

        // ✅ Show result with String.format
        String result = String.format(
                "Pay: $%.2f | OT: $%.2f | Total: $%.2f | Tax: $%.2f",
                basePay, overtimePay, totalPay, tax
        );
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
