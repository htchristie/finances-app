package com.fmu.financesapp.dao;

import android.util.Log;

import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Category;
import com.fmu.financesapp.model.TransactionParent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AccountDao{
    private final static ArrayList<Account> accounts = new ArrayList<>();
    private static int interableId = 0;

    public void save(Account account){
        account.setId(interableId);
        accounts.add(account);
        interableId++;
    }

    public double generalBalance(){
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

    public ArrayList filterByMonth(int month){
        return new ArrayList();
    }

    public Double filterByCategory(String category){
        double value = 0;
        for (Account account: accounts) {
            if(category.equals(account.getCategory()) && !account.isType()){
                    value+= account.getValue();
                }
        }
        return value;
    }

    public ArrayList<Account> all(){ return new ArrayList<>(accounts);}

    public String formatCurrency(Double value) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat brazilFormat = NumberFormat.getCurrencyInstance(brazil);
        return brazilFormat.format(value);
    }

    public void edit(Account account) {
        Account findedAccount = searchAccount(account);
        if(findedAccount != null){
            //findedAccount.getId(); ID PARA COLOCAR NO WHERE
            int psAccount = accounts.indexOf(findedAccount);
            accounts.set(psAccount, account);
            Log.i("Edit ", "FOI EDITADO");
        }
    }

    public void remove(Account account )
    {
        Account categoryAccount = searchAccount(account);
        if(categoryAccount != null){
            //findedCategory.getId(); ID PARA COLOCAR NO WHERE
            accounts.remove(categoryAccount);
        }
    }

    private Account searchAccount(Account account) {
        Account foundAccount = null;
        for(Account a: accounts){
            if(a.getId() == account.getId()){
                return a;
            }
        }
        return foundAccount;
    }
}
