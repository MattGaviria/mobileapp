package com.example.a1_cesar_gaviriasepulveda;

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

    // Declare variables
    private EditText etHours, etRate;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements to variables
        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        btnCalculate = findViewById(R.id.btnCalculate);

        // Button click
        btnCalculate.setOnClickListener(v -> computeAndLog());
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
        double pay, overtimePay = 0.0;
        if (no_of_hours <= 40) {
            pay = no_of_hours * hourly_rate;
        } else {
            pay = 40 * hourly_rate;
            overtimePay = (no_of_hours - 40) * hourly_rate * 1.5;
        }

        double totalPay = pay + overtimePay;
        double tax = totalPay * 0.18;

        // Save payment
        Payment payment = new Payment(no_of_hours, hourly_rate, pay, overtimePay, totalPay, tax);
        PaymentRepository.add(payment);

        // Show result
        String payStub = String.format(
                "Pay: $%.2f | OT: $%.2f | Total: $%.2f | Tax: $%.2f",
                pay, overtimePay, totalPay, tax);
        Toast.makeText(this, payStub, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_payments) {
            startActivity(new Intent(this, DetailActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item); // fixed this line
    }
}
