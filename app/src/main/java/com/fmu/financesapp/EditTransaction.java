package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.fmu.financesapp.databinding.ActivityEditGoalBinding;
import com.fmu.financesapp.databinding.ActivityEditTransactionBinding;

public class EditTransaction extends AppCompatActivity {

    private ActivityEditTransactionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar editTransactionToolbar = binding.toolbarEditTransaction;
        setSupportActionBar(editTransactionToolbar);

        ActionBar editTransactionEditBar = getSupportActionBar();
        if (editTransactionEditBar != null) {
            editTransactionEditBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}