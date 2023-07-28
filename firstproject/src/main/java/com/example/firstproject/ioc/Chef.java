package com.example.firstproject.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Chef {

    private IngredientFactory ingredientFactory;

    @Autowired
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu){
//        재료 준비
        Ingredient ingredient = ingredientFactory.get(menu);
//        요리 반환
//        return pork.getName() + "으로 만든 " + menu;
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
