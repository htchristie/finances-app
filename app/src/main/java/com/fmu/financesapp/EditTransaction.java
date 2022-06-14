package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;

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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbEditProfit:
                if (checked)
                    setProfitAdapter();
                break;
            case R.id.rbEditExpense:
                if (checked)
                    setExpenseAdapter();
                break;
        }
    }

    private void setExpenseAdapter() {
        String[] expenseCategories = getResources().getStringArray(R.array.categories_expense);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, expenseCategories);
        AutoCompleteTextView autoCompleteTextView = binding.tvTransactionCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

    private void setProfitAdapter() {
        String[] profitCategories = getResources().getStringArray(R.array.categories_profit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, profitCategories);
        AutoCompleteTextView autoCompleteTextView = binding.tvTransactionCategory;
        autoCompleteTextView.setAdapter(adapter);
    }
}