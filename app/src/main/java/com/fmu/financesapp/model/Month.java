package com.fmu.financesapp.model;

public class Month {
    private String monthTitle;
    private int monthNumber;

    public Month(String montTitle, int monthNumber) {
        this.monthTitle = montTitle;
        this.monthNumber = monthNumber;
    }

    public String getMonthTitle() {
        return monthTitle;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

}
