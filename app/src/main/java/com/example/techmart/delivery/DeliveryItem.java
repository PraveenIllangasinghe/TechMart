package com.example.techmart.delivery;

public class DeliveryItem {
    String amount;
    String id;
    String item;
    String location;
    String contact;
    String email;
    String customerName;

    public DeliveryItem(String amount, String id, String item, String location, String contact, String email, String customerName) {
        this.amount = amount;
        this.id = id;
        this.item = item;
        this.location = location;
        this.contact = contact;
        this.email = email;
        this.customerName = customerName;
    }

    public DeliveryItem() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
