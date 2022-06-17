package com.fmu.financesapp.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Account implements Serializable {
    private int id = 0;
    private String name;
    private boolean type;
    private Double value;
    private String category;

    public Account(String name, Double value, String category, Boolean type){
        this.name = name;
        this.value = value;
        this.category = category;
        this.type = type;
    }

    public Account(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }


    public void setId(int interableId) {
        this.id = interableId;
    }
}
