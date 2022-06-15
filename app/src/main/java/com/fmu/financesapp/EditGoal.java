package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fmu.financesapp.adapters.PlanningListAdapter;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;
import com.fmu.financesapp.databinding.ActivityEditGoalBinding;
import com.fmu.financesapp.model.Category;

public class EditGoal extends AppCompatActivity{

    private ActivityEditGoalBinding binding;
    private Category category;
    private CategoryDao account = new CategoryDao();
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
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        for(Category c: account.all()){
                if(c.getId() == id){
                    category = c;
                }
                    Log.i("TESTE", Integer.toString(id));
        }

        initToolbar();
        initAutoCompleteCateogories();


    }
    private void getViews() {
        goalText = findViewById(R.id.etGoalAmount);
        categorie = findViewById(R.id.tvGoalCategory);
        btnSave = findViewById(R.id.btnGoalSave);
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