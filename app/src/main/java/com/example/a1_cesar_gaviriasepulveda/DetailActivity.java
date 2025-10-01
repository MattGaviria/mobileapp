package com.example.a1_cesar_gaviriasepulveda;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        ArrayAdapter<Payment> adapter = new ArrayAdapter<> ( this,
                android.R.layout.simple_list_item_1,
                PaymentRepository.all()
        );
        listView.SetAdapter(adapter)


    }
}