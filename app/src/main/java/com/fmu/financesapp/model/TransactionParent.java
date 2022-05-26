package com.fmu.financesapp.model;
import java.util.List;

public class TransactionParent  {
    String dataTitle;
    List<Account> accountsList;

    public TransactionParent(String dataTitle, List<Account> accountsList) {
        this.dataTitle = dataTitle;
        this.accountsList = accountsList;
    }
    public String getDataTitle() {
        return dataTitle;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }
}
