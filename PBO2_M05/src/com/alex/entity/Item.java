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
public class Item {

    private String id;
    private String name;
    private int price;
    private String recommended;

    public Item() {
    }

    public Item(String id, String name, int price, String recommended) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.recommended = recommended;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;

    }
}
