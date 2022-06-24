package com.fmu.financesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.fmu.financesapp.databinding.ActivityMainBinding;
import com.fmu.financesapp.fragments.HomeFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;

    private FloatingActionButton fabTransactions;
    private FloatingActionButton fabGoals;
    private TextView tvTransactions, tvGoals;
    private View overlay;

    private Animation fabOpen, fabClose;

    private Boolean isVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavigation();
        initHomeRecycle(savedInstanceState);

        FloatingActionButton fabMain = binding.fabExpand;
        fabTransactions = binding.fabTransaction;
        fabGoals = binding.fabGoal;
        tvTransactions = binding.tvFabTransaction;
        tvGoals = binding.tvFabGoal;
        overlay = binding.viewOverlay;

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        fabMain.setOnClickListener(view -> {
            if (!isVisible) {
                showOverlayView();
                fabMain.startAnimation(fabOpen);
                fabTransactions.show();
                tvTransactions.setVisibility(View.VISIBLE);
                fabGoals.show();
                tvGoals.setVisibility(View.VISIBLE);
            } else {
                hideOverlayView();
                fabMain.startAnimation(fabClose);
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

    public void launchAddTransactionActivity(View view) {
        Intent intent = new Intent(this, AddTransaction.class);
        startActivity(intent);
    }

    public void launchAddGoalActivity(View view) {
        Intent intent = new Intent(this, AddGoal.class);
        startActivity(intent);
    }

}

