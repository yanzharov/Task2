/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.stax;

/**
 *
 * @author admin
 */
public enum MenuTagName {
    MENU,CATEGORY,DISH,DISH_NAME,DISH_DESCRIPTION,DISH_PORTION,DISH_PRICE,DISH_CHOICES,DISH_CHOICE,DISH_CHOICE_NAME,DISH_CHOICE_PRICE;

    public static MenuTagName getElementTagName(String element) {
	switch (element) {
	    case "menu":
        	return MENU;
            case "category":
		return CATEGORY;
	    case "dish":
		return DISH;
	    case "dish-name":
		return DISH_NAME;
	    case "dish-description":
		return DISH_DESCRIPTION;
	    case "dish-portion":
		return DISH_PORTION;
            case "dish-price":
		return DISH_PRICE; 
            case "dish-choices":
		return DISH_CHOICES; 
            case "dish-choice":
		return DISH_CHOICE; 
            case "dish-choice-name":
		return DISH_CHOICE_NAME; 
            case "dish-choice-price":
		return DISH_CHOICE_PRICE;    
	    default:
		throw new EnumConstantNotPresentException(MenuTagName.class, element);
	}
    }

}
