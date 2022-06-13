package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.fmu.financesapp.databinding.ActivityAddGoalBinding;
import com.fmu.financesapp.databinding.ActivityMainBinding;

public class AddGoal extends AppCompatActivity {

    private ActivityAddGoalBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        String[] categories = getResources().getStringArray(R.array.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, categories);
        AutoCompleteTextView autoCompleteTextView = binding.tvGoalCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar addGoalToolbar = binding.toolbarAddGoal;
        setSupportActionBar(addGoalToolbar);

        ActionBar addGoalActionBar = getSupportActionBar();
        if (addGoalActionBar != null) {
            addGoalActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}