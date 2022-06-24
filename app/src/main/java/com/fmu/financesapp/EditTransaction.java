package com.fmu.financesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.databinding.ActivityEditTransactionBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.fmu.financesapp.model.Account;

public class EditTransaction extends AppCompatActivity {

    private ActivityEditTransactionBinding binding;
    private AccountDao accountDao = new AccountDao();
    private Account account = new Account();
    private int id;
    private TextView amount;
    private TextView description;
    private TextView date;
    private AutoCompleteTextView category;
    private Boolean type;
    private Button btnSave;
    private View btnDelete;

    TextInputEditText tvTransactionDate;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolBar();
        getViews();
        initFields();
        setBtnClick();
        initDatePicker();
    }

    private void initDatePicker() {
        tvTransactionDate = binding.tvTransactionDate;

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvTransactionDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    EditTransaction.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener, year, month, day);
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
            }        };
    }

    private void initFields() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        for(Account c: accountDao.all()){
            if(c.getId() == id){
                account = c;
            }
        }
        amount.setText(Double.toString(account.getValue()));
        description.setText(account.getName());
        category.setText(account.getCategory());
        date.setText(account.getDate());
        setRadioButton(account.isType());

    }

    private void setRadioButton(Boolean typeTransAction) {
        if(typeTransAction){
            RadioButton profitRadio = findViewById(R.id.rbEditProfit);
            profitRadio.setChecked(true);
            setProfitAdapter();
        }else{
            RadioButton expensiveRadio = findViewById(R.id.rbEditExpense);
            expensiveRadio.setChecked(true);
            setExpenseAdapter();
        }
    }

    private void fillFields() {
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

    private void setBtnClick() {
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                fillFields();
                accountDao.edit(account);

                goToHomeScreen();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountDao.remove(account);
                goToHomeScreen();
            }
        });

    }

    private void goToHomeScreen() {
        Intent intent = new Intent(EditTransaction.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getViews() {
        amount = findViewById(R.id.etTransactionAmount);
        description = findViewById(R.id.etTransactionDescription);
        category = findViewById(R.id.tvTransactionCategory);
        date = findViewById(R.id.tvTransactionDate);
        btnSave = findViewById(R.id.btnEditTransactionSave);
        btnDelete = findViewById(R.id.editTransactionDelete);
    }

    private void toolBar() {
        Toolbar editTransactionToolbar = binding.toolbarEditTransaction;
        setSupportActionBar(editTransactionToolbar);

        ActionBar editTransactionEditBar = getSupportActionBar();
        if (editTransactionEditBar != null) {
            editTransactionEditBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_transaction_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onRadioButtonClicked(View view) {
        category.setText("");
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