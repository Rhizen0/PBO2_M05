/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.entity;

/**
 *
 * @author Alexius Surya 1772043
 */
public class Menu {

    private int id;
    private String name;
    private int price;
    private boolean recommended;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu(int id, String name, int price, boolean recommended, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.recommended = recommended;
        this.category = category;
    }

}
