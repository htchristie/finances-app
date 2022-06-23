package com.fmu.financesapp.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fmu.financesapp.EditGoal;
import com.fmu.financesapp.R;
import com.fmu.financesapp.adapters.PlanningListAdapter;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.fragments.GoalsFragment;

public class GoalsActivity extends AppCompatActivity {

    private CategoryDao categoryDao = new CategoryDao(this);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_goals);
        initGoalRecycle(savedInstanceState);

    }

    private void initGoalRecycle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.rvPlanningCard, GoalsFragment.class, null)
                    .commit();
        }
    }
}
