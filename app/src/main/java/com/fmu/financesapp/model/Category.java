package com.fmu.financesapp.model;

import java.io.Serializable;

public class Category implements Serializable {

    private int id = 0;
    private String name;
    private Double budget;
    private String icon;

    public Category(String name, Double budget, String icon){
        this.name = name;
        this.budget = budget;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBudget() {
        return budget;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(int interableId) {
        this.id = interableId;
    }

}
