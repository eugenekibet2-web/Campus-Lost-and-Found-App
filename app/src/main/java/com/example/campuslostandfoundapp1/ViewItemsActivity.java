package com.example.campuslostandfoundapp1; // KEEP YOUR PACKAGE NAME

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ViewItemsActivity extends AppCompatActivity {

    private ItemAdapter adapter;
    private EditText etSearch;
    private Spinner spinnerCategory, spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        etSearch = findViewById(R.id.etSearch);
        spinnerCategory = findViewById(R.id.spinnerFilterCategory);
        spinnerStatus = findViewById(R.id.spinnerFilterStatus);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewItems);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(this, new ArrayList<>(ItemRepository.getItems()));
        recyclerView.setAdapter(adapter);

        // Live search listener
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { applyFilters(); }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Category & Status filter listeners
        AdapterView.OnItemSelectedListener filterListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { applyFilters(); }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spinnerCategory.setOnItemSelectedListener(filterListener);
        spinnerStatus.setOnItemSelectedListener(filterListener);
    }

    private void applyFilters() {
        String query = etSearch.getText().toString().toLowerCase().trim();
        String selectedCategory = spinnerCategory.getSelectedItem().toString();
        String selectedStatus = spinnerStatus.getSelectedItem().toString();

        List<Item> filtered = new ArrayList<>();
        for (Item item : ItemRepository.getItems()) {
            boolean matchesName = item.getName().toLowerCase().contains(query);
            boolean matchesCategory = selectedCategory.equals("All Categories") || item.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchesStatus = selectedStatus.equals("All Status") || item.getStatus().equalsIgnoreCase(selectedStatus);

            if (matchesName && matchesCategory && matchesStatus) {
                filtered.add(item);
            }
        }
        adapter.updateList(filtered);
    }
}
