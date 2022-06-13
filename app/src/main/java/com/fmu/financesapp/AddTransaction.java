package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;

public class AddTransaction extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        String[] categories = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, categories);
        AutoCompleteTextView autoCompleteTextView = binding.tvTransactionCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar addTransactionToolbar = binding.toolbarTransactions;
        setSupportActionBar(addTransactionToolbar);

        ActionBar addTransactionActionBar = getSupportActionBar();
        if (addTransactionActionBar != null) {
            addTransactionActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}