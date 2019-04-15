package com.example.softspaceposjm.Model;

import com.google.firebase.database.DatabaseReference;

public class service_Info {
    private String userid,serviceID, eqType, nationality, status,addressInfo;
    private String NoOfPax;

    public service_Info(String userid, String serviceID, String eqType, String nationality, String status, String NoOfPax,String addressInfo){
        this.userid = userid;
        this.serviceID = serviceID;
        this.eqType = eqType;
        this.nationality = nationality;
        this.status = status;
        this.NoOfPax = NoOfPax;;
        this.addressInfo = addressInfo;
    }

    public static void LogFirebase(DatabaseReference serviceRef, DatabaseReference userRef, String userId, String eqType, String nationality,String status, String NoOfPax,String addressInfo) {
        String key = serviceRef.push().getKey();
        service_Info service_info = new service_Info(userId, key, eqType, nationality, status,NoOfPax,addressInfo);
        serviceRef.child(userId).child(key).setValue(service_info);
        userRef.child(userId).child("Services").child(key).setValue(service_info);
    }

    public String getUserid() {
        return userid;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getEqType() {
        return eqType;
    }

    public String getNationality() {
        return nationality;
    }

    public String getNoOfPax() {
        return NoOfPax;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public void setEqType(String eqType) {
        this.eqType = eqType;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setNoOfPax(String noOfPax) {
        NoOfPax = noOfPax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
