package com.fmu.financesapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fmu.financesapp.EditTransaction;
import com.fmu.financesapp.R;
import com.fmu.financesapp.adapters.TransactionRecycleAdapters.TransactionParentAdapter;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.interfaces.TransactionInterface;
import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Month;
import com.fmu.financesapp.model.TransactionParent;

import java.util.ArrayList;


public class TransactionsFragment extends Fragment implements TransactionInterface {

    private final AccountDao accountList = new AccountDao();
    private ArrayList<TransactionParent> transactionParentsList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        setTextData(view);
        initRecycle(view);
        return view;
    }

    private void setTextData(View view) {
        TextView tvTrProfitDisplay = view.findViewById(R.id.tvTrProfitDisplay);
        TextView tvTrExpensesDisplay = view.findViewById(R.id.tvTrExpensesDisplay);
        tvTrProfitDisplay.setText(accountList.formatCurrency(accountList.positiveBalance()));
        tvTrExpensesDisplay.setText(accountList.formatCurrency(accountList.negativeBalance()));
    }

    private void initRecycle(View view) {
        prepareTransaActionParent();

        RecyclerView rvTransactions = view.findViewById(R.id.rvTransactionCard);
        TransactionParentAdapter parentAdapter = new TransactionParentAdapter(transactionParentsList, this);
        rvTransactions.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvTransactions.setAdapter(parentAdapter);
    }

    private void prepareTransaActionParent() {

        for (Month m: accountList.allMonths()) {
                ArrayList<Account> accounts = accountList.filterByMonth(m.getMonthNumber());
                if(!accounts.isEmpty()){
                    transactionParentsList.add(new TransactionParent(m.getMonthTitle(), accounts));
                }
        }
    }

    @Override
    public void onItemClick(int ps) {
        Intent intent = new Intent(getContext(), EditTransaction.class);
        intent.putExtra("id", ps);
        startActivity(intent);
    }
}