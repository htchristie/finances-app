package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;
import com.fmu.financesapp.databinding.ActivityEditGoalBinding;

public class EditGoal extends AppCompatActivity {

    private ActivityEditGoalBinding binding;

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
}