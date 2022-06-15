package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;
import com.fmu.financesapp.databinding.ActivityEditGoalBinding;

public class EditGoal extends AppCompatActivity {

    private ActivityEditGoalBinding binding;

    protected void onResume() {
        super.onResume();
        String[] categories = getResources().getStringArray(R.array.categories_expense);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, categories);
        AutoCompleteTextView autoCompleteTextView = binding.tvGoalCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar editGoalToolbar = binding.toolbarAddGoal;
        setSupportActionBar(editGoalToolbar);

        ActionBar editGoalEditBar = getSupportActionBar();
        if (editGoalEditBar != null) {
            editGoalEditBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_goal_menu, menu);
        return true;
    }
}