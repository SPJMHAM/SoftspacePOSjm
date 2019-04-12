package com.example.softspaceposjm.Model;

import com.google.firebase.database.DatabaseReference;

public class service_Info {
    private String userid,serviceID, eqType, nationality;
    private int NoOfPax;

    public service_Info(String userid, String serviceID, String eqType, String nationality, Integer NoOfPax){
        this.userid = userid;
        this.serviceID = serviceID;
        this.eqType = eqType;
        this.nationality = nationality;
        this.NoOfPax = NoOfPax;
    }

    public static void LogFirebase(DatabaseReference serviceRef, DatabaseReference userRef, String userId, String eqType, String nationality, Integer NoOfPax) {
        String key = serviceRef.push().getKey();
        service_Info service_info = new service_Info(userId, key,  eqType, nationality, NoOfPax);
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

    public int getNoOfPax() {
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

    public void setNoOfPax(int noOfPax) {
        NoOfPax = noOfPax;
    }
}
