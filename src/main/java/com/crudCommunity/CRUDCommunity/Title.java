package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;

public class Title {
    public int postNumber;
    public String postTitle;
    public String postWriter;
    public String uploadDate;
    public void SetUploadDate(Timestamp date)
    {
        String str = date.toString();
        String hours = str.substring(11, 13);
        String minutes = str.substring(14, 16);
        uploadDate = hours + ":" + minutes;
    }
}
