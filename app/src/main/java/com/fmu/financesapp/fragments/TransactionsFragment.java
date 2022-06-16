package com.fmu.financesapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fmu.financesapp.EditGoal;
import com.fmu.financesapp.EditTransaction;
import com.fmu.financesapp.R;
import com.fmu.financesapp.adapters.TransactionRycleAdapters.TransactionParentAdapter;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.interfaces.TransactionInterface;
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
        initRycle(view);
        return view;

    }

    private void setTextData(View view) {
        TextView tvTrProfitDisplay = view.findViewById(R.id.tvTrProfitDisplay);
        TextView tvTrExpensesDisplay = view.findViewById(R.id.tvTrExpensesDisplay);
        tvTrProfitDisplay.setText(accountList.formartCurrency(accountList.positiveBalance()));
        tvTrExpensesDisplay.setText(accountList.formartCurrency(accountList.negativeBalance()));
    }

    private void initRycle(View view) {
        transactionParentsList.add(new TransactionParent("20 de maio", accountList.all()));
        transactionParentsList.add(new TransactionParent("21 de maio", accountList.all()));
        transactionParentsList.add(new TransactionParent("22 de maio", accountList.all()));

        RecyclerView rvTransactions = view.findViewById(R.id.rvTransactionCard);
        TransactionParentAdapter parentAdapter = new TransactionParentAdapter(transactionParentsList, this);
        rvTransactions.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvTransactions.setAdapter(parentAdapter);
    }

    @Override
    public void onItemClick(int ps) {
        Intent intent = new Intent(getContext(), EditTransaction.class);
        intent.putExtra("id", ps);
        startActivity(intent);
    }
}