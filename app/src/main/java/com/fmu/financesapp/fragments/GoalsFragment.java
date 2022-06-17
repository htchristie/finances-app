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

import com.fmu.financesapp.AddGoal;
import com.fmu.financesapp.EditGoal;
import com.fmu.financesapp.R;
import com.fmu.financesapp.adapters.PlanningListAdapter;
import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.dao.UserDao;
import com.fmu.financesapp.databinding.FragmentGoalsBinding;
import com.fmu.financesapp.interfaces.GoalRycleInterface;

public class GoalsFragment extends Fragment implements GoalRycleInterface {
    private final CategoryDao categoryList = new CategoryDao();
    private final AccountDao accountDao = new AccountDao();
    private PlanningListAdapter adapter = new PlanningListAdapter(categoryList.all(), this);
    private FragmentGoalsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals, container, false);
        TextView userBudget = view.findViewById(R.id.tvTotalBudgetDisplay);
        TextView userSpend = view.findViewById(R.id.tvBudgetDisplay);

        userSpend.setText(accountDao.formartCurrency(accountDao.negativeBalance()));
        userBudget.setText("/ "+accountDao.formartCurrency(categoryList.userBudgetCateories()));
        initRycleView(view);
        return view;
    }

    private void initRycleView(View view) {
        RecyclerView rvPlanningCard = view.findViewById(R.id.rvPlanningCard) ;
        rvPlanningCard.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvPlanningCard.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int ps) {
        Intent intent = new Intent(getContext(), EditGoal.class);
        intent.putExtra("id", ps);
        startActivity(intent);
    }
}