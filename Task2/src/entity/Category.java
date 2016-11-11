/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Dish> list;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getList() {
        return list;
    }

    public void setList(List<Dish> list) {
        this.list = list;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + name.hashCode();
        hash = 47 * hash + list.hashCode();
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
        final Category other = (Category) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.list.equals(other.list)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "name=" + name + ", list=" + list + '}';
    }
    
}
