/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Choice implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int price;

    public Choice() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + id;
        hash = 53 * hash + name.hashCode();
        hash = 53 * hash + price;
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
        final Choice other = (Choice) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Choice{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }
    
}
