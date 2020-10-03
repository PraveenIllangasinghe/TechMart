package com.example.techmart.ishini;

import java.io.Serializable;

public class OrderModel implements Serializable {

    private int OrderId;
    private String OrderDate;
    private Float totalAmount;
    private Float finalAmount;
    private Float deliveryFee;
    private String DeliveryAddress;

    public Float calculateTotal(){
        float result = totalAmount + deliveryFee;
        setFinalAmount(result);
        return result;
    }
    public Float getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Float finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Float getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Float deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    private String status;
    private int riderID;

    public OrderModel(int orderId, String orderDate, Float totalAmount, String deliveryAddress, String status, int riderID) {
        OrderId = orderId;
        OrderDate = orderDate;
        this.totalAmount = totalAmount;
        DeliveryAddress = deliveryAddress;
        this.status = status;
        this.riderID = riderID;
    }

    public OrderModel(int orderId, String orderDate, Float totalAmount, Float finalAmount, Float deliveryFee, String deliveryAddress, String status, int riderID) {
        OrderId = orderId;
        OrderDate = orderDate;
        this.totalAmount = totalAmount;
        this.finalAmount = finalAmount;
        this.deliveryFee = deliveryFee;
        DeliveryAddress = deliveryAddress;
        this.status = status;
        this.riderID = riderID;
    }

    public OrderModel() {
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
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

    public int getRiderID() {
        return riderID;
    }

    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

}