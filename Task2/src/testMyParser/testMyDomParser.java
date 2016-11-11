/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testMyParser;

import entity.Document;
import entity.Element;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Category;
import entity.Choice;
import entity.Dish;
import service.DomParser;
import service.impl.DomFactory;

/**
 *
 * @author admin
 */
public class testMyDomParser {
    public static void main(String...args)throws FileNotFoundException,IOException{
        
        DomFactory factory=DomFactory.getInstance();
        DomParser parser=factory.create();
        parser.parse("G:\\xml\\myXMLDocument2.xml");
        Document document=parser.getDocument();
        Element root=document.getDocumentElement();
        List<Element> catElements = root.getElementsByTagName("category");
        List<Category> catList = new ArrayList<Category>();
        List<Dish> dishList = new ArrayList<Dish>();
        List<Choice> choiceList = new ArrayList<Choice>();
        Category cat = null;
        Dish dish=null;
        Choice choice=null;
        
       for (int i = 0; i < catElements.size(); i++) {
            cat = new Category();
            Element catElement = (Element) catElements.get(i);
	    cat.setName(catElement.getAttribute("name"));	    
            List<Element> dishElements=catElement.getElementsByTagName("dish");
            cat.setList(getDishList(dishElements,dish,dishList,choice,choiceList));
            dishList=new ArrayList<Dish>();
            catList.add(cat);
	}
       
       print(catList);
    }
    
    private static List<Choice> getChoiceList(List<Element> choiceElements, Choice choice, List<Choice> choiceList){
        for(int k=0;k<choiceElements.size();k++){
            choice=new Choice();
            Element choiceElement=(Element) choiceElements.get(k);
            choice.setId(Integer.parseInt(choiceElement.getAttribute("id")));
            choice.setName(getSingleChildContent(choiceElement, "dish_choice_name"));
            choice.setPrice(Integer.parseInt(getSingleChildContent(choiceElement, "dish_choice_price")));
            choiceList.add(choice);
        }
        return choiceList;
    }
    
    private static List<Dish> getDishList(List<Element> dishElements, Dish dish, List<Dish> dishList,Choice choice,List<Choice> choiceList){
        for(int j=0;j<dishElements.size();j++){
            dish=new Dish();
            Element dishElement = (Element) dishElements.get(j);
            dish.setName(getSingleChildContent(dishElement, "dish_name"));
            dish.setDescription(getSingleChildContent(dishElement, "dish_description"));
            dish.setPortion(getSingleChildContent(dishElement, "dish_portion"));
            dish.setPrice(Integer.parseInt(getSingleChildContent(dishElement, "dish_price")));
            List<Element> choiceElements=dishElement.getElementsByTagName("dish_choice");
            dish.setChoices(getChoiceList(choiceElements,choice,choiceList));
            choiceList=new ArrayList<Choice>();
            dishList.add(dish);
        }
        return dishList;
    }
    
    private static void print(List<Category> catList){
        for(Category c : catList){
            System.out.println(c.getName());
            List<Dish> dishes=c.getList();
            for(Dish d:dishes){
                System.out.println("      "+d.getName() + "       " + d.getDescription()+"       "+d.getPortion()+"       "+d.getPrice());
                List<Choice> choices=d.getChoices();
                for(Choice ch:choices){
                    System.out.println("               "+ch.getId()+"       "+ch.getName()+"       "+ch.getPrice());
                }
            }
        }
    }
    
    private static String getSingleChildContent(Element element, String childName){
        String content=null;
        List<Element> nlist = element.getElementsByTagName(childName);
        Element child=null;
        try{
        child = (Element) nlist.get(0);
        }
        catch(IndexOutOfBoundsException e){      
        }
        try{
            content=child.getTextContent().trim();
        }
        catch(NullPointerException e){
            if(childName.equals("dish_price") || childName.equals("dish_choice_price")){
                content="0";
            }
        }
	return content;
    }
}
