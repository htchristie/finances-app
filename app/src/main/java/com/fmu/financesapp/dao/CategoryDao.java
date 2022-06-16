package com.fmu.financesapp.dao;

import com.fmu.financesapp.model.Category;

import java.util.ArrayList;

public class CategoryDao {

        private final static ArrayList<Category> categories = new ArrayList<>();
        private static int interableId = 0;

    public void save(Category account){
        account.setId(interableId);
        categories.add(account);
        interableId++;
    }

    public void remove(Category category )
    {
        Category categoryFinded = searchCategory(category);
        if(categoryFinded != null){
            categories.remove(categoryFinded);
        }

    }

    public void edit(Category category) {

        Category findedCategory = searchCategory(category);
        if(findedCategory != null){
            int psCategory = categories.indexOf(findedCategory);
            categories.set(psCategory, category);
        }

    }

    private Category searchCategory(Category category) {
             Category findedCategory = null;
             for(Category c: categories){
                 if(c.getId() == category.getId()){
                     return c;
                 }
             }
             return findedCategory;
    }


    public ArrayList<Category> all(){ return new ArrayList<>(categories);}


}
