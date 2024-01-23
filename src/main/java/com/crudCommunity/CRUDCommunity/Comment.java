package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;

public class Comment {
    public int postNumber;
    public String comment;
    public String uploadDate;
    public Comment()
    {
        
    }
    public Comment(int postNumber, String comment)
    {
        this.postNumber = postNumber;
        this.comment = comment;
    }
    public void SetUploadDate(Timestamp date)
    {
        String currentTime = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        String str = date.toString();
        if (currentTime.substring(0, 10).equals(str.substring(0, 10)))
            uploadDate = str.substring(11, 16);
        else
            uploadDate = str.substring(0, 10);
        ;
    }
}
