package com.example.campuslostandfoundapp1; // KEEP YOUR PACKAGE NAME

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnReportLost = findViewById(R.id.btnReportLost);
        Button btnReportFound = findViewById(R.id.btnReportFound);
        Button btnViewItems = findViewById(R.id.btnViewItems);

        btnReportLost.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            intent.putExtra("STATUS_TYPE", "Lost");
            startActivity(intent);
        });

        btnReportFound.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReportActivity.class);
            intent.putExtra("STATUS_TYPE", "Found");
            startActivity(intent);
        });

        btnViewItems.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewItemsActivity.class);
            startActivity(intent);
        });
    }
}