package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;

public class Title {
    public int postNumber;
    public String postTitle;
    public String postWriter;
    public String uploadDate;
    public void SetUploadDate(Timestamp date)
    {
        String currentTime = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        String str = date.toString();
        if(currentTime.substring(0,10).equals(str.substring(0,10)))
            uploadDate = str.substring(11, 16);
        else
            uploadDate = str.substring(0,10);;
    }
}
