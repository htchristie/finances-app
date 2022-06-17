package com.fmu.financesapp.dao;

import android.util.Log;

import com.fmu.financesapp.model.Account;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AccountDao{
    private final static ArrayList<Account> accounts = new ArrayList<>();
    private static int interableId = 1;


    public void save(Account account){
        account.setId(interableId);
        accounts.add(account);
        interableId++;
    }

    public double generalBalace(){
        double negativeValue = negativeBalance();
        double positiveValue = positiveBalance();
        return positiveValue - negativeValue;
    }

    public double negativeBalance(){
        double negative = 0;
        for (Account account :accounts) {
            if(!account.isType()){
                negative += account.getValue();
            }
        }
        return negative;
    }

    public double positiveBalance(){
        double positive = 0;
        for (Account account:accounts) {
            if(account.isType()){
                positive += account.getValue();
            }
        }
        return positive;
    }

    public void filterByMonth(){

    }

    public Double filterByCategory(String category){
        double value = 0;
        for (Account account: accounts) {
            if(category.equals(account.getCategory()) && account.isType() == false){
                    value+= account.getValue();
                }

        }
        return value;
    }

    public ArrayList<Account> all(){ return new ArrayList<>(accounts);}

    public String formartCurrency(Double value) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat brazilFormat = NumberFormat.getCurrencyInstance(brazil);
        return brazilFormat.format(value);
    }
}
