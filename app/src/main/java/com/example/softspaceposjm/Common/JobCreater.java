package com.example.softspaceposjm.Common;


public class JobCreater {

    public static String Eid;


    public JobCreater(){

    }

    public JobCreater(String Eid) {
        this.Eid = Eid;
    }

    public static String getEid() {
        return Eid;
    }

    public static void setEid(String eid) {
        Eid = eid;
    }
}
