package com.fmu.financesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.fmu.financesapp.dao.AccountDao;
import com.fmu.financesapp.dao.CategoryDao;
import com.fmu.financesapp.dao.UserDao;
import com.fmu.financesapp.databinding.ActivityMainBinding;
import com.fmu.financesapp.fragments.HomeFragment;
import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Category;
import com.fmu.financesapp.model.User;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding ;
    private final AccountDao accountList = new AccountDao();
    private final CategoryDao categoryList = new CategoryDao();
    private final UserDao user = new UserDao();
    private NavHostFragment navHostFragment;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUsersMockData();
        setUserMock();
        setCategoryMockData();
        initNavigation();
        initHomeRecyle(savedInstanceState);

    }

    private void initHomeRecyle(Bundle savedInstanceState) {
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

    private void setUsersMockData(){
        accountList.save(new Account("teste", 1000.0, "Compras", false));
        accountList.save(new Account("teste", 1000.0, "Compras", true));
        accountList.save(new Account("teste", 1000.0, "Cartão", false));
        accountList.save(new Account("teste", 1000.0, "Casa", false));
        accountList.save(new Account("teste", 1000.0, "Casa", true));
    }

    private void setCategoryMockData(){
        categoryList.save(new Category("Compras", 4000.0, "ic_food"));
        categoryList.save(new Category("Cartão", 1000.0, "ic_food"));
        categoryList.save(new Category("Casa", 1000.0, "ic_food"));
    }
    private void setUserMock(){
        user.save(new User("João Victor", 3000.0));

    }
}

