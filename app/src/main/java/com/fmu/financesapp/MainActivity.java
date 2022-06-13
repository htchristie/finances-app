package com.fmu.financesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.databinding.ActivityMainBinding;
import com.fmu.financesapp.fragments.HomeFragment;
import com.fmu.financesapp.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private final AccountDao accountList = new AccountDao();

    private FloatingActionButton fabTransactions;
    private FloatingActionButton fabGoals;
    private TextView tvTransactions, tvGoals;
    private View overlay;

    private Boolean isVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setMockData();
        initNavigation();
        initHomeRecycle(savedInstanceState);

        FloatingActionButton fabMain = binding.fabExpand;
        fabTransactions = binding.fabTransaction;
        fabGoals = binding.fabGoal;
        tvTransactions = binding.tvFabTransaction;
        tvGoals = binding.tvFabGoal;
        overlay = binding.viewOverlay;

        fabMain.setOnClickListener(view -> {
            if (!isVisible) {
                showOverlayView();
                fabTransactions.show();
                tvTransactions.setVisibility(View.VISIBLE);
                fabGoals.show();
                tvGoals.setVisibility(View.VISIBLE);
            } else {
                hideOverlayView();
                fabTransactions.hide();
                tvTransactions.setVisibility(View.GONE);
                fabGoals.hide();
                tvGoals.setVisibility(View.GONE);
            }
            isVisible = !isVisible;
        });

    }

    private void showOverlayView() {
        overlay.animate().alpha(0.7f);
    }

    private void hideOverlayView() {
        overlay.animate().alpha(0f);
    }

    private void initHomeRecycle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.srlRecentActivity, HomeFragment.class, null)
                    .commit();
        }
    }

    private void initNavigation() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }

    private void setMockData() {
        accountList.save(new Account("teste", 1000.0, "Mercado Açai", false));
        accountList.save(new Account("teste", 1000.0, "Vendas", true));
        accountList.save(new Account("teste", 1000.0, "Contas", false));
        accountList.save(new Account("teste", 1000.0, "Lanche", false));
        accountList.save(new Account("teste", 1000.0, "Comes", true));
    }
}

