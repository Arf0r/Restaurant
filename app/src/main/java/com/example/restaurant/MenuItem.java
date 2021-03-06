package com.example.restaurant;
import java.io.Serializable;

public class MenuItem implements Serializable {
    // Initialize variables
    private String name;
    private String description;
    private String imageUrl;
    private int price;
    private String category;

    // Contructor
    public MenuItem(String name, String description, String image, int price, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = image;
        this.price = price;
        this.category = category;
    }

    // Getters and setters for the variables of this class
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
