package com.example.techmart;

public class OrderItem {

    private String OrderDate;
    private Float totalAmount;
    private String item1;
    private String item2;
    private int itemQuantity1;
    private int itemQuantity2;
    private String itemDescription1;
    private String itemDescription2;
    private String DeliveryAddress;
    private String status;
    private String orderId;


    public OrderItem() {
    }

    public OrderItem(String orderDate, Float totalAmount, String item1, String item2, int itemQuantity1, int itemQuantity2, String itemDescription1, String itemDescription2, String deliveryAddress, String status, String orderId) {
        OrderDate = orderDate;
        this.totalAmount = totalAmount;
        this.item1 = item1;
        this.item2 = item2;
        this.itemQuantity1 = itemQuantity1;
        this.itemQuantity2 = itemQuantity2;
        this.itemDescription1 = itemDescription1;
        this.itemDescription2 = itemDescription2;
        DeliveryAddress = deliveryAddress;
        this.status = status;
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public int getItemQuantity1() {
        return itemQuantity1;
    }

    public void setItemQuantity1(int itemQuantity1) {
        this.itemQuantity1 = itemQuantity1;
    }

    public int getItemQuantity2() {
        return itemQuantity2;
    }

    public void setItemQuantity2(int itemQuantity2) {
        this.itemQuantity2 = itemQuantity2;
    }

    public String getItemDescription1() {
        return itemDescription1;
    }

    public void setItemDescription1(String itemDescription1) {
        this.itemDescription1 = itemDescription1;
    }

    public String getItemDescription2() {
        return itemDescription2;
    }

    public void setItemDescription2(String itemDescription2) {
        this.itemDescription2 = itemDescription2;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
