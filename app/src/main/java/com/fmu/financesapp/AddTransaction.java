package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddTransaction extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;

    TextInputEditText tvTransactionDate;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvTransactionDate = binding.tvTransactionDate;

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvTransactionDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddTransaction.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener, year, month, day);
            datePickerDialog.getDatePicker().setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        setListener = (datePicker, year1, month1, day1) -> {
            month1 = month1 +1;
            String date = day1 +"/"+ month1 +"/"+ year1;
            tvTransactionDate.setText(date);
        };

        Toolbar addTransactionToolbar = binding.toolbarTransactions;
        setSupportActionBar(addTransactionToolbar);

        ActionBar addTransactionActionBar = getSupportActionBar();
        if (addTransactionActionBar != null) {
            addTransactionActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbProfit:
                if (checked)
                    setProfitAdapter();
                    break;
            case R.id.rbExpense:
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