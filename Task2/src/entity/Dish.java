/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import entity.Choice;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Dish implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private String portion;
    private int price;
    private List<Choice> choices;

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + name.hashCode();
        hash = 83 * hash + description.hashCode();
        hash = 83 * hash + portion.hashCode();
        hash = 83 * hash + price;
        hash = 83 * hash + choices.hashCode();
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
        final Dish other = (Dish) obj;
        if (this.price != other.price) {
            return false;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.description.equals(other.description)) {
            return false;
        }
        if (!this.portion.equals(other.portion)) {
            return false;
        }
        if (!this.choices.equals(other.choices)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dish{" + "name=" + name + ", description=" + description + ", portion=" + portion + ", price=" + price + ", choices=" + choices + '}';
    }
    
}
