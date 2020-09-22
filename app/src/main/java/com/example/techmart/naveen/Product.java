package com.example.techmart.naveen;

public class Product {

    private String Description;
    private Float Price;
    private String ProductId;
    private String ProductName;



    public Product() {
    }

    public Product(String description, Float price, String productId, String productName) {
        Description = description;
        Price = price;
        ProductId = productId;
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
