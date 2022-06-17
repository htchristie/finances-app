package com.fmu.financesapp;

import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.databinding.ActivityAddTransactionBinding;
import com.fmu.financesapp.model.Account;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTransaction extends AppCompatActivity {

    private ActivityAddTransactionBinding binding;
    private AccountDao accountDao= new AccountDao();
    private Account account = new Account();
    private TextView amount;
    private TextView description;
    private TextView date;
    private AutoCompleteTextView category;
    private Boolean type;
    private Button btnSave;


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
        toolBar();
        setProfitAdapter();
        StartDatePicker();
        getViews();
        btnClick();

    }

    private void StartDatePicker() {
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                tvTransactionDate.setText(sdf.format(sdf.parse(date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
    }

    private void btnClick() {
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    fillFields();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                accountDao.save(account);
                Intent intent = new Intent(AddTransaction.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fillFields() throws ParseException {
        Double amountAccount  = Double.parseDouble(amount.getText().toString());
        String descriptionAccount = description.getText().toString();
        String dateAccount = date.getText().toString();
        String categoryAccount = category.getText().toString();

        account.setName(descriptionAccount);
        account.setValue(amountAccount);
        account.setType(type);
        account.setCategory(categoryAccount);
        account.setDate(dateAccount);
    }



    private void getViews() {
        amount = findViewById(R.id.etTransactionAmount);
        description = findViewById(R.id.etTransactionDescription);
        category = findViewById(R.id.tvTransactionCategory);
        date = findViewById(R.id.tvTransactionDate);
        btnSave = findViewById(R.id.btnTransactionsSave);
    }

    private void toolBar() {
        Toolbar addTransactionToolbar = binding.toolbarTransactions;
        setSupportActionBar(addTransactionToolbar);

        ActionBar addTransactionActionBar = getSupportActionBar();
        if (addTransactionActionBar != null) {
            addTransactionActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onRadioButtonClicked(View view) {
        category.setText("");
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
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
        type = false;
        String[] expenseCategories = getResources().getStringArray(R.array.categories_expense);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, expenseCategories);
        AutoCompleteTextView autoCompleteTextView = binding.tvTransactionCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

    private void setProfitAdapter() {
        type = true;
        String[] profitCategories = getResources().getStringArray(R.array.categories_profit);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.transactions_dropdown_item, profitCategories);
        AutoCompleteTextView autoCompleteTextView = binding.tvTransactionCategory;
        autoCompleteTextView.setAdapter(adapter);
    }

}