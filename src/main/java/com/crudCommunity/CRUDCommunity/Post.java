package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;

public class Post {
    public String title;
    public String writer;
    public String uploadDate;
    public String text;
    public String password;
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
