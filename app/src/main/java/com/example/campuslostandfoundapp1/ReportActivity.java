package com.example.campuslostandfoundapp1;// KEEP YOUR PACKAGE NAME

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private String statusType = "Lost";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        if (getIntent().hasExtra("STATUS_TYPE")) {
            statusType = getIntent().getStringExtra("STATUS_TYPE");
        }

        TextView tvTitle = findViewById(R.id.tvReportTitle);
        tvTitle.setText("Report " + statusType + " Item");

        EditText etName = findViewById(R.id.etItemName);
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        EditText etDescription = findViewById(R.id.etDescription);
        EditText etLocation = findViewById(R.id.etLocation);
        EditText etDate = findViewById(R.id.etDate);
        EditText etContact = findViewById(R.id.etContact);
        Button btnSubmit = findViewById(R.id.btnSubmitReport);

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();
            String desc = etDescription.getText().toString().trim();
            String location = etLocation.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String contact = etContact.getText().toString().trim();

            if (name.isEmpty() || desc.isEmpty() || location.isEmpty() || date.isEmpty() || contact.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            Item newItem = new Item(name, desc, location, date, contact, category, statusType);
            ItemRepository.addItem(newItem);

            // Task 5: Confirmation feedback via AlertDialog
            new AlertDialog.Builder(this)
                    .setTitle("Report Submitted")
                    .setMessage("Your " + statusType.toLowerCase() + " item ('" + name + "') has been posted.")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .show();
        });
    }
}
