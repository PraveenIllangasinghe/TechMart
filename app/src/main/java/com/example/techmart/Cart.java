package com.example.techmart;

public class Cart {

    private String brand;
    private String pName;

    public Cart() {
    }

    public Cart(String brand, String pName) {
        this.brand = brand;
        this.pName = pName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
