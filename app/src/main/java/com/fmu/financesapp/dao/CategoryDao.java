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
        Category categoryFound = searchCategory(category);
        if(categoryFound != null){
            //findedCategory.getId(); ID PARA COLOCAR NO WHERE

            categories.remove(categoryFound);
        }

    }

    public void edit(Category category) {

        Category foundCategory = searchCategory(category);
        if(foundCategory != null){
            //foundCategory.getId(); ID PARA COLOCAR NO WHERE

            int psCategory = categories.indexOf(foundCategory);
            categories.set(psCategory, category);
        }

    }

    private Category searchCategory(Category category) {
             Category foundCategory = null;
             for(Category c: categories){
                 if(c.getId() == category.getId()){
                     return c;
                 }
             }
             return foundCategory;
    }

    public ArrayList<Category> all(){ return new ArrayList<>(categories);}

    public Double userBudgetCategories(){
        Double value = 0.0;
                for (Category c: categories){
                    value += c.getBudget();
                }
        return value;
    }
}
