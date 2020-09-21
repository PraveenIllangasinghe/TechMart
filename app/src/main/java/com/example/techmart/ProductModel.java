package com.example.techmart;

public class ProductModel {

    private String description;
    private Float price;
    private String productId;
    private String productName;
//    private String productImage;

    public ProductModel() {
    }

    public ProductModel(String description, Float price, String productId, String productName) {
        this.description = description;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
//        this.productImage = productImage;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

 /*   public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }       */
}
