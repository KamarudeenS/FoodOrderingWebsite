package com.food.model;

public class CartItem {

    private int menuId;
    private String name;
    private int price;
    private int quantity;
    private int restaurantId;
    public CartItem() {}

    public CartItem(int menuId, String name, int price, int quantity) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // This method calculates total price for this cart item
    public int getTotalAmount() {
        return price * quantity;
    }
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
