package com.fmu.financesapp.dao;

import android.util.Log;

import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Month;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class AccountDao{
    private final static ArrayList<Account> accounts = new ArrayList<>();
    private final static ArrayList<Month> months = new ArrayList<>(
            Arrays.asList(
                    new Month("Dezembro",12),
                    new Month("Novembro",11),
                    new Month("Outubro",10),
                    new Month("Setembro",9),
                    new Month("Agosto",8),
                    new Month("Julho",7),
                    new Month("Junho",6),
                    new Month("Maio",5),
                    new Month("Abril",4),
                    new Month("Março",3),
                    new Month("Fevereiro",2),
                    new Month("Janeiro",1)
            ));
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
        ArrayList<Account> accountsMonthArray = new ArrayList<>();
        for (Account account : accounts) {
                int dateMonth = Integer.parseInt(formatMonth(account.getDate()));
                if(month == dateMonth){
                     accountsMonthArray.add(account);
                }
        }
        return accountsMonthArray;
    }

    private String formatMonth(String parse) {
        String[] splitArray = parse.split("/");
        return splitArray[1];
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

    public ArrayList<Month> allMonths(){ return new ArrayList<>(months);}


    public String formatCurrency(Double value) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat brazilFormat = NumberFormat.getCurrencyInstance(brazil);
        return brazilFormat.format(value);
    }

    public void edit(Account account) {
        Account findedAccount = searchAccount(account);
        if(findedAccount != null){
            int psAccount = accounts.indexOf(findedAccount);
            accounts.set(psAccount, account);
            Log.i("Edit ", "FOI EDITADO");
        }
    }

    public void remove(Account account )
    {
        Account categoryAccount = searchAccount(account);
        if(categoryAccount != null){
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
