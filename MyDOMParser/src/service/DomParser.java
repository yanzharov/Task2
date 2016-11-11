/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import entity.Document;

/**
 *
 * @author admin
 */
public interface DomParser {
    
    public void parse(String fileName) throws FileNotFoundException,IOException;
    public Document getDocument();
    
}
