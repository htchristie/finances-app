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
import com.fmu.financesapp.databinding.ActivityEditGoalBinding;
import com.fmu.financesapp.model.Category;

public class EditGoal extends AppCompatActivity{

    private ActivityEditGoalBinding binding;
    private Category category;
    private CategoryDao categoryDao = new CategoryDao();
    private EditText goalText;
    private Button btnSave;
    private AutoCompleteTextView categorie;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditGoalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getViews();
        fillFieldsToedit();
        btnClick();
        initToolbar();
        initAutoCompleteCateogories();


    }

    private void fillFieldsToedit() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        for(Category c: categoryDao.all()){
            if(c.getId() == id){
                    category = c;
                }
        }
        goalText.setText(Double.toString(category.getBudget()));
        categorie.setText(category.getName());
    }

    private void getViews() {
        goalText = findViewById(R.id.etGoalAmount);
        categorie = findViewById(R.id.tvGoalCategory);
        btnSave = findViewById(R.id.btnEditGoalSave);
    }
    private void btnClick() {
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                fillFields();
                categoryDao.edit(category);
                Intent intent = new Intent(EditGoal.this,
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
    private void initToolbar() {
        Toolbar editGoalToolbar = binding.toolbarAddGoal;
        setSupportActionBar(editGoalToolbar);

        ActionBar editGoalEditBar = getSupportActionBar();
        if (editGoalEditBar != null) {
            editGoalEditBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}