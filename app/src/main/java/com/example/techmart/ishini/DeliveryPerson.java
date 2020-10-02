package com.example.techmart.ishini;

import java.io.Serializable;

public class DeliveryPerson implements Serializable {
    private String OrderNo;
    private String RiderName;
    private int  RiderID;
    private int  VehicalID;
    private String RiderContactNum;
    private String RiderEmail;
    private String RiderAddress;

    public String getRiderContactNum() {
        return RiderContactNum;
    }

    public void setRiderContactNum(String riderContactNum) {
        RiderContactNum = riderContactNum;
    }

    public String getRiderEmail() {
        return RiderEmail;
    }

    public void setRiderEmail(String riderEmail) {
        RiderEmail = riderEmail;
    }

    public String getRiderAddress() {
        return RiderAddress;
    }

    public void setRiderAddress(String riderAddress) {
        RiderAddress = riderAddress;
    }
    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getRiderName() {
        return RiderName;
    }

    public void setRiderName(String riderName) {
        RiderName = riderName;
    }

    public int getRiderID() {
        return RiderID;
    }

    public void setRiderID(int riderID) {
        RiderID = riderID;
    }

    public int getVehicalID() {
        return VehicalID;
    }

    public void setVehicalID(int vehicalID) {
        VehicalID = vehicalID;
    }

    public DeliveryPerson() {

    }

    public DeliveryPerson(String orderNo, String riderName, int riderID, int vehicalID, String riderContactNum, String riderEmail, String riderAddress) {
        OrderNo = orderNo;
        RiderName = riderName;
        RiderID = riderID;
        VehicalID = vehicalID;
        RiderContactNum = riderContactNum;
        RiderEmail = riderEmail;
        RiderAddress = riderAddress;
    }
}
