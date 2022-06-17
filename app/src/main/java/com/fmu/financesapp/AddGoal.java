package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.databinding.ActivityAddGoalBinding;
import com.fmu.financesapp.fragments.HomeFragment;
import com.fmu.financesapp.model.Category;

public class AddGoal extends AppCompatActivity {

    private ActivityAddGoalBinding binding;
    private CategoryDao categoryDao = new CategoryDao();
    private Category category = new Category();

    private EditText goalText;
    private Button btnSave;
    private AutoCompleteTextView categorie;

    @Override
    protected void onResume() {
        super.onResume();
        initAutoCompleteCateogories();
        getViews();
        btnClick();
    }

    private void getViews() {
        goalText = findViewById(R.id.etGoalAmount);
        categorie = findViewById(R.id.tvGoalCategory);
        btnSave = findViewById(R.id.btnGoalSave);
    }

        private void btnClick() {
            btnSave.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    fillFields();
                    categoryDao.save(category);
                    Intent intent = new Intent(AddGoal.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    private void fillFields() {
        String categoryName  = categorie.getText().toString();
        String budget = goalText.getText().toString();
        Double dbBudget = Double.parseDouble(budget);
        category.setName(categoryName);
        category.setBudget(dbBudget);
    }

    private void initAutoCompleteCateogories() {
        String[] categories = getResources().getStringArray(R.array.categories_expense);
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