/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.sax;

import java.util.ArrayList;
import java.util.List;
import entity.Category;
import entity.Choice;
import entity.Dish;
import static javax.swing.Action.NAME;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author admin
 */
public class MenuSaxHandler extends DefaultHandler {
    private List<Category> catList = new ArrayList<Category>();
    private Category cat;
    private Dish dish;
    private Choice choice;
    private StringBuilder text;

    public List<Category> getCategoryList() {
	return catList;
    }

    public void startDocument() throws SAXException {
	System.out.println("Parsing started.");
    }

    public void endDocument() throws SAXException {
	System.out.println("Parsing ended.");
    }

    public void characters(char[] buffer, int start, int length) {
	text.append(buffer, start, length);
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	text = new StringBuilder();
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName){
            case CATEGORY:
	        cat = new Category();
                cat.setName(attributes.getValue("name"));
                cat.setList(new ArrayList<Dish>());
                break;
            case DISH:
		dish = new Dish();
                dish.setChoices(new ArrayList<Choice>());
                break;   
            case DISH_CHOICE:
		choice = new Choice();
		choice.setId((Integer.parseInt(attributes.getValue("id"))));
                break;    
	}
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));
	switch (tagName){
            case CATEGORY:
                catList.add(cat);
		cat = null;
		break;
            case DISH:
		cat.getList().add(dish);
	        dish = null;
		break;     
            case DISH_NAME:
                dish.setName(text.toString());
		break;  
            case DISH_DESCRIPTION:
	        dish.setDescription(text.toString());
	        break;    
            case DISH_PORTION:
		dish.setPortion(text.toString());
		break;  
            case DISH_PRICE:
	        dish.setPrice(Integer.parseInt(text.toString()));
		break;     
            case DISH_CHOICE:
	        dish.getChoices().add(choice);
	        break;
            case DISH_CHOICE_NAME:
		choice.setName(text.toString());
	        break;  
            case DISH_CHOICE_PRICE:
	        choice.setPrice(Integer.parseInt(text.toString()));
		break;      
	}
    }

}
