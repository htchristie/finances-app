package com.fmu.financesapp.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fmu.financesapp.R;
import com.fmu.financesapp.fragments.TransactionsFragment;

public class PlanningActivity extends AppCompatActivity {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_planning);

    }

    private void initPlanningRecyle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.rvTransactionCard, TransactionsFragment.class, null)
                    .commit();
        }
    }
}
