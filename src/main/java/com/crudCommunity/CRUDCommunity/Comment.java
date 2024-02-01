package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="commentinfo")
public class Comment {
    @Id
    @Column(name="number")
    public Long number;
    @Column(name="post_number")
    public int postNumber;
    @Column(name="text")
    public String text;
    @Column(name="upload_date")
    public String uploadDate;

    @Transient
    public String dateInfo;
    public Comment()
    {
        
    }
    public Comment(int postNumber, String comment)
    {
        this.postNumber = postNumber;
        this.text = comment;
    }
    public void SetUploadDate()
    {
        String currentTime = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        String str = uploadDate.toString();
        if (currentTime.substring(0, 10).equals(str.substring(0, 10)))
            dateInfo = str.substring(11, 16);
        else
            dateInfo = str.substring(0, 10);
    }
}
