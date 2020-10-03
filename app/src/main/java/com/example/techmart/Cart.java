package com.example.techmart;

public class Cart {

    private String productId;
    private String productName;
    private Float unitPrice;
    private String productDescription;
    private Float quantity;
    private Float netAmount;

    public Cart() {
    }

    public Cart(String productId, String productName, Float unitPrice, String productDescription, Float quantity, Float netAmount) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.productDescription = productDescription;
        this.quantity = quantity;
        this.netAmount = netAmount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Float netAmount) {
        this.netAmount = netAmount;
    }
}
