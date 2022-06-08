package com.fmu.financesapp.dao;


import com.fmu.financesapp.model.User;

import java.util.ArrayList;

public class UserDao {
    private String name;
    private Double value;

    public void save(User user) {
        name = user.getName();
        value = user.getBudget();
    }

    public double getUserBudget(){
        return 4000.0;
    }

}
