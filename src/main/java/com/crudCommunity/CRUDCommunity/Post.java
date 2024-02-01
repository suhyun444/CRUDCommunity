package com.crudCommunity.CRUDCommunity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="postlist")
public class Post {
    @Id
    @Column(name = "number")
    public Long number;
    @Column(name = "title")
    public String title;
    @Column(name = "writer")
    public String writer;
    @Column(name = "password")
    public String password;
    @Column(name = "upload_date")
    public String uploadDate;
    @Column(name = "text")
    public String text;

    @Transient
    public String dateInfo;
    public Post()
    {

    }
    public Post(Long number,String title,String writer,String password, String uploadDate,String text)
    {
        this.number = number;
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.uploadDate = uploadDate;
        this.text = text;
    }
    public void SetUploadDate()
    {
        String currentTime = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        String str = uploadDate;
        if(currentTime.substring(0,10).equals(str.substring(0,10)))
            dateInfo = str.substring(11, 16);
        else
            dateInfo = str.substring(0,10);
    }
}
