/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dom;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import static java.awt.SystemColor.menu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Category;
import entity.Choice;
import entity.Dish;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author admin
 */
public class DomEx {

    public static void main(String[] args) throws SAXException, IOException {
	DOMParser parser = new DOMParser(); 
        parser.parse("G:\\xml\\myXMLDocument2.xml");
	Document document = parser.getDocument();
	Element root = document.getDocumentElement();
        NodeList catNodes = root.getElementsByTagName("category");
        List<Category> catList = new ArrayList<Category>();
        List<Dish> dishList = new ArrayList<Dish>();
        List<Choice> choiceList = new ArrayList<Choice>();
        Category cat = null;
        Dish dish=null;
        Choice choice=null;
		
	for (int i = 0; i < catNodes.getLength(); i++) {
            cat = new Category();
            Element catElement = (Element) catNodes.item(i);
	    cat.setName(catElement.getAttribute("name"));	    
            NodeList dishNodes=catElement.getElementsByTagName("dish");
            cat.setList(getDishList(dishNodes,dish,dishList,choice,choiceList));
            dishList=new ArrayList<Dish>();
            catList.add(cat);
	}

	print(catList);

    }
    
    private static List<Choice> getChoiceList(NodeList choiceNodes, Choice choice, List<Choice> choiceList){
        for(int k=0;k<choiceNodes.getLength();k++){
            choice=new Choice();
            Element choiceElement=(Element) choiceNodes.item(k);
            choice.setId(Integer.parseInt(choiceElement.getAttribute("id")));
            choice.setName(getSingleChildContent(choiceElement, "dish-choice-name"));
            choice.setPrice(Integer.parseInt(getSingleChildContent(choiceElement, "dish-choice-price")));
            choiceList.add(choice);
        }
        return choiceList;
    }
    
    private static List<Dish> getDishList(NodeList dishNodes, Dish dish, List<Dish> dishList,Choice choice,List<Choice> choiceList){
        for(int j=0;j<dishNodes.getLength();j++){
            dish=new Dish();
            Element dishElement = (Element) dishNodes.item(j);
            dish.setName(getSingleChildContent(dishElement, "dish-name"));
            dish.setDescription(getSingleChildContent(dishElement, "dish-description"));
            dish.setPortion(getSingleChildContent(dishElement, "dish-portion"));
            dish.setPrice(Integer.parseInt(getSingleChildContent(dishElement, "dish-price")));
            NodeList choiceNodes=dishElement.getElementsByTagName("dish-choice");
            dish.setChoices(getChoiceList(choiceNodes,choice,choiceList));
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
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        try{
            content=child.getTextContent().trim();
        }
        catch(NullPointerException e){
            if(childName.equals("dish-price") || childName.equals("dish-choice-price")){
                content="0";
            }
        }
	return content;
    }

}
