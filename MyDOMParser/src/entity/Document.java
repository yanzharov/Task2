/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Document {
    
    private Element documentElement;

    public Document() {
    }

    public Document(Element rootElement) {
        documentElement=new Element();
        documentElement.setTagName("document");
        documentElement.setChilds(new ArrayList<Element>());
        documentElement.getChilds().add(rootElement);
    }

    public Element getDocumentElement() {
        return documentElement;
    }
     
}
