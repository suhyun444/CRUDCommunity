package com.crudCommunity.CRUDCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Service
public class MainModel {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void AddPost(String postWriter, String postPassword, String postTitle, String postText)
    {
        int index = (int)jdbcTemplate.queryForMap("select postAmount from communityinfo").get("postAmount");
        jdbcTemplate.update("INSERT INTO postlist VALUES(?,?,?,?,?)",index,postTitle,postWriter,postPassword,new java.sql.Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("INSERT INTO postinfo VALUES(?,?)",index,postText);
        jdbcTemplate.update("update communityinfo set postAmount = ? where postAmount = ?",index + 1,index);
        System.out.println("success");
    }
}
