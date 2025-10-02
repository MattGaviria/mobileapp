package com.example.a1_cesar_gaviriasepulveda;

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

        //For displaying the logs
        ListView listView = findViewById(R.id.listViewPayments);
        Button btnBack = findViewById(R.id.btnBack);

        //Create the array Adpater
        ArrayAdapter<Payment> adapter = new ArrayAdapter<> ( this,
                android.R.layout.simple_list_item_1,
                PaymentRepository.all()
        );
        listView.setAdapter(adapter);

        //return button
        btnBack.setOnClickListener(v -> {
            finish();
        });



    }


}