package com.fmu.financesapp.model;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account implements Serializable {
    private int id = 0;
    private String name;
    private boolean type;
    private Double value;
    private String category;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Account(String name, Double value, String category, Boolean type, String date){
        this.name = name;
        this.value = value;
        this.category = category;
        this.type = type;
        this.date = date;
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
