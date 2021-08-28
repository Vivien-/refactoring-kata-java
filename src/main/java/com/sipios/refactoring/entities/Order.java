package com.sipios.refactoring.entities;

public class Order {

    private Item[] items;
    private String type;

    public Order(Item[] is, String t) {
        this.items = is;
        this.type = t;
    }

    public Order() {
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
