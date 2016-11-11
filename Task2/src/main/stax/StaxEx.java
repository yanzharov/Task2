/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamException;
import entity.Category;
import entity.Choice;
import entity.Dish;

/**
 *
 * @author admin
 */
public class StaxEx {
    public static void main(String[] args) throws FileNotFoundException {
	XMLInputFactory inputFactory = XMLInputFactory.newInstance();
	try {
	    InputStream input = new FileInputStream("G:\\xml\\myXMLDocument2.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Category> menu = process(reader);
	    for(Category cat : menu){
	    System.out.println(cat.getName());
            List<Dish> dishes=cat.getList();
                for(Dish dish:dishes){
                    System.out.println("      "+dish.getName() + "       " + dish.getDescription()+"       "+dish.getPortion()+"       "+dish.getPrice());
                    List<Choice> choices=dish.getChoices();
                    for(Choice choice:choices){
                        System.out.println("               "+choice.getId()+"       "+choice.getName()+"       "+choice.getPrice());
                    }
                }
            }
	} 
        catch (XMLStreamException e) {
	    e.printStackTrace();
	} 
    }

    private static List<Category> process(XMLStreamReader reader) throws XMLStreamException {
	List<Category> catList = new ArrayList<Category>();
	Category cat=null;
        Dish dish=null;
        Choice choice=null;
	MenuTagName elementName = null;

	while (reader.hasNext()) {
	    int type = reader.next();
	    switch (type) {
		case XMLStreamConstants.START_ELEMENT:
		    elementName = MenuTagName.getElementTagName(reader.getLocalName());
		    switch (elementName) {
			case CATEGORY:
                            cat = new Category();
                            cat.setName(reader.getAttributeValue(null, "name"));
                            cat.setList(new ArrayList<Dish>());
                            break;
                        case DISH:
                            dish = new Dish();
                            dish.setChoices(new ArrayList<Choice>());
                            break;   
                        case DISH_CHOICE:
                            choice = new Choice();
                            choice.setId((Integer.parseInt(reader.getAttributeValue(null, "id"))));
                            break;  
		    }
		break;
		case XMLStreamConstants.CHARACTERS:
		    String text = reader.getText().trim();
		    if (text.isEmpty()) {
		        break;
		    }
		    switch (elementName) {
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
                        case DISH_CHOICE_NAME:
                            choice.setName(text.toString());
                            break;  
                        case DISH_CHOICE_PRICE:
                            choice.setPrice(Integer.parseInt(text.toString()));
                            break;
		    }
		break;
		case XMLStreamConstants.END_ELEMENT:
		    elementName = MenuTagName.getElementTagName(reader.getLocalName());
		    switch (elementName) {
		        case CATEGORY:
                            catList.add(cat);
                            cat = null;
                            break;
                        case DISH:
                            cat.getList().add(dish);
                            dish = null;
                            break;
                        case DISH_CHOICE:
                            dish.getChoices().add(choice);
                            break;
		    }
	    }
	}
	return catList;
    }
}
