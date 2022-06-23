package com.fmu.financesapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.fmu.financesapp.model.Account;
import com.fmu.financesapp.model.Category;
import com.fmu.financesapp.model.TransactionParent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AccountDao extends SQLiteOpenHelper {
    private final static ArrayList<Account> accounts = new ArrayList<>();
    private static int interableId = 0;
    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "bd_projeto.db";
    private static final String TABELA = "account";

    public AccountDao(Context context) {
        super(context, BANCO_CLIENTE,null, VERSAO_BANCO);
    }


    void addAccount(Account account){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idcategoria", account.getCategory());
        values.put("idaccount", account.getId());
        values.put("valorCompra", account.getValue());
        values.put("tipo", account.getId());
        values.put("createdat", account.getDate());

        db.insert(TABELA, null, values);
        db.close();
    }

    void update(Account account){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("valorCompra", account.getName());

        String argumentos = String.valueOf(account.getId());
        db.update(TABELA, values, "idaccount" + "=?", new String[]{argumentos});
        db.close();
    }

    void delete(Account account) {

        SQLiteDatabase db = this.getWritableDatabase();

        String argumentos = String.valueOf(account.getId());
        db.delete(TABELA, "idaccount" + "=?", new String[]{argumentos});
        db.close();
    }

    public void save(Account account){
        account.setId(interableId);
        accounts.add(account);
        interableId++;
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
        addAccount(account);
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

    public String formartCurrency(Double value) {
        Locale brazil = new Locale("pt", "BR");
        NumberFormat brazilFormat = NumberFormat.getCurrencyInstance(brazil);
        return brazilFormat.format(value);
    }

    public void edit(Account account) {
        Account findedAccount = searchAccount(account);
        if(findedAccount != null){
            //findedAccount.getId(); ID PARA COLOCAR NO WHERE
            update(account);
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
            delete(account);
            accounts.remove(categoryAccount);
        }
    }

    private Account searchAccount(Account account) {
        Account findedAccount = null;
        for(Account a: accounts){
            if(a.getId() == account.getId()){
                return a;
            }
        }
        return findedAccount;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table account(idcategoria integer,idaccount integer,valorCompra REAL,tipo integer,createdat Date)";
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
