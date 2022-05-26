package com.fmu.financesapp.dao;

import com.fmu.financesapp.model.Category;

import java.util.ArrayList;

public class CategoryDao {

        private final static ArrayList<Category> categories = new ArrayList<>();
        private static int interableId = 1;

    public void save(Category account){
        account.setId(interableId);
        categories.add(account);
        interableId++;
    }



    public ArrayList<Category> all(){ return new ArrayList<>(categories);}

}
