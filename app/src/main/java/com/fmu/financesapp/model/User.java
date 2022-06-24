package com.fmu.financesapp.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private Double budget;

    public User(String name, Double budget){
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public Double getBudget() {
        return budget;
    }

}