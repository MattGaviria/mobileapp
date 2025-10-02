package com.example.a1_cesar_gaviriasepulveda;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ListView listView = findViewById(R.id.listViewPayments);
        Button btnBack = findViewById(R.id.btnBack);
        ArrayAdapter<Payment> adapter = new ArrayAdapter<> ( this,
                android.R.layout.simple_list_item_1,
                PaymentRepository.all()
        );
        listView.setAdapter(adapter);

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // optional: closes DetailActivity so it doesn’t stay in stack
        });



    }


}