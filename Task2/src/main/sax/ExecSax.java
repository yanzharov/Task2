/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.sax;

import java.io.IOException;
import java.util.List;
import entity.Category;
import entity.Choice;
import entity.Dish;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author admin
 */
public class ExecSax {  
    public static void main(String[] args) throws SAXException, IOException{
	XMLReader reader = XMLReaderFactory.createXMLReader();
	MenuSaxHandler handler = new MenuSaxHandler();
	reader.setContentHandler(handler);
	reader.parse(new InputSource("G:\\xml\\myXMLDocument2.xml"));
	List<Category> menu = handler.getCategoryList();	    
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
}
