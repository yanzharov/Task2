/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Element implements Serializable{
    private static final long serialVersionUID = 1L;
    private String tagName;
    private String textContent;
    private Map<String,String> attributes;
    private List<Element> childs;

    public Element() {
    }
    
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
    public List<Element> getChilds() {
        return childs;
    }

    public void setChilds(List<Element> childs) {
        this.childs = childs;
    }
    
    public List<Element> getElementsByTagName(String s){
        List<Element> tagList=new ArrayList<Element>();
        putElement(this,s,tagList);
        return tagList;
    }
    
    public void putElement(Element root,String s,List<Element> tagList){
        List<Element> branches=root.getChilds();
        for(Element branch:branches){
            if(branch.getTagName().equals(s)){
                tagList.add(branch);
            }
            if(!branch.getChilds().isEmpty()){
                putElement(branch,s,tagList);
            }
        }
    }
    
    public String getAttribute(String s){
        return this.attributes.get(s);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + tagName.hashCode();
        hash = 67 * hash + textContent.hashCode();
        hash = 67 * hash + attributes.hashCode();
        hash = 67 * hash + childs.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Element other = (Element) obj;
        if (!this.tagName.equals(other.tagName)) {
            return false;
        }
        if (!this.textContent.equals(other.textContent)) {
            return false;
        }
        if (!this.attributes.equals(other.attributes)) {
            return false;
        }
        if (!this.childs.equals(other.childs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Element{" + "tagName=" + tagName + ", textContent=" + textContent + ", attributes=" + attributes + ", childs=" + childs + '}';
    } 
    
}
