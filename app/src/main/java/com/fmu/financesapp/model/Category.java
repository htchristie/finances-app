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

    public Category() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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

    public void setName(String categoryName) {
        this.name = categoryName;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
