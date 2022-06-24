package com.fmu.financesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fmu.financesapp.R;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.adapters.AccountsListAdapter;

public class HomeFragment extends Fragment {

    private final AccountDao accountList = new AccountDao();
    private AccountsListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        headerHome(view);
        listRecycleView(view);
        SwipeRefreshLayout swipeContainer = view.findViewById(R.id.srlRecentActivity);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData(swipeContainer);
            }
        });
        return view;
    }

    private void updateData(SwipeRefreshLayout swipe) {
        adapter.updateData();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(false);
    }

    private void headerHome(View view) {
        TextView profitsText = view.findViewById(R.id.tvProfitDisplay);
        TextView expensesText = view.findViewById(R.id.tvExpensesDisplay);
        TextView generalBalance = view.findViewById(R.id.tvBalanceDisplay);
        profitsText.setText(formatValues(accountList.positiveBalance()));
        expensesText.setText(formatValues(accountList.negativeBalance()));
        generalBalance.setText(formatValues(accountList.generalBalance()));
    }

    private String formatValues(Double value){
        return accountList.formatCurrency(value);
    }

    private void listRecycleView(View view) {
        adapter = new AccountsListAdapter(accountList.all());
        RecyclerView rvRecentActivity = view.findViewById(R.id.rvRecentActivity) ;
        rvRecentActivity.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvRecentActivity.setAdapter(adapter);
    }
}