/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import entity.Document;
import entity.Element;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import service.DomParser;


/**
 *
 * @author admin
 */
public class BaseDomParser implements DomParser{
    private Stack stack=new Stack();
    private Element rootElement=null;
    private Element element=null;
    private StringBuilder text=new StringBuilder();
    
    public void parse(String fileName) throws FileNotFoundException,IOException{
        BufferedReader reader=new BufferedReader(new FileReader(fileName));
        String s;
        Matcher m;
        Pattern p = Pattern.compile("<(/?\\w+)(\\s*)((\\s*)(\\w+)=['\"](.+)['\"](\\s*))*>");          
        
	while((s=reader.readLine())!=null){
            if(!s.startsWith("<?xml")){
                s=s.replaceAll("-", "_");
                m = p.matcher(s);
                character(s);
                while(m.find()){
                    if(m.group(1).charAt(0)!='/'){ 
                        openElement(m,s);
                    }
                    if(m.group(1).charAt(0)=='/'){
                        closeElement();
                    }
                } 
            }
        }
        reader.close();
    }
    
    public void openElement(Matcher m,String s){
        element=new Element();
        element.setTagName(m.group(1)); 
        element.setAttributes(readAttributes(s));
        element.setChilds(new ArrayList<Element>());
        if(rootElement==null){
            rootElement=element;
            text=new StringBuilder();
        }
        stack.push(element);
    }
    
    public void closeElement(){
        Element pop=(Element)stack.pop();
        pop.setTextContent(text.toString());
        text=new StringBuilder();
        if(!stack.isEmpty()){
            Element base=(Element)stack.peek();
            base.getChilds().add(pop);
        }
    }
    
    public void character(String s){
        String o=s.replaceAll("<(/?\\w+)((\\s*)(\\w+)=['\"](.+)['\"](\\s*))*>", "");
        text.append(o.trim());
    }
    
    public Map<String,String> readAttributes(String s){
        Map<String,String> map=new HashMap<String,String>();
        Pattern p = Pattern.compile("(\\w+)=['\"](.+)['\"]");  
        Matcher m= p.matcher(s);
        while(m.find()){
            map.put(m.group(1), m.group(2));
        }
        return map;
    }
    
    public Document getDocument(){
        Document document=new Document(rootElement);
        return document;
    }

}

