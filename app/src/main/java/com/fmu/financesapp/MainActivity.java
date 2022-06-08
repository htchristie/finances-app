package com.fmu.financesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.databinding.ActivityMainBinding;
import com.fmu.financesapp.fragments.HomeFragment;
import com.fmu.financesapp.model.Account;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final AccountDao accountList = new AccountDao();
    private NavHostFragment navHostFragment;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setMockData();
        initNavigation();
        initHomeRecycle(savedInstanceState);

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
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }

    private void setMockData(){
        accountList.save(new Account("teste", 1000.0, "Mercado Açai", false));
        accountList.save(new Account("teste", 1000.0, "Vendas", true));
        accountList.save(new Account("teste", 1000.0, "Contas", false));
        accountList.save(new Account("teste", 1000.0, "Lanche", false));
        accountList.save(new Account("teste", 1000.0, "Comissão", true));
    }
}

