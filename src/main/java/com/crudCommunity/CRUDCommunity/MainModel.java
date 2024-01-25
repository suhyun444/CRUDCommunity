package com.crudCommunity.CRUDCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainModel {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void AddPost(Post post)
    {
        int index = (int)jdbcTemplate.queryForMap("select postAmount from communityinfo").get("postAmount");
        jdbcTemplate.update("INSERT INTO postlist VALUES(?,?,?,?,?)",index, post.title, post.writer, post.password,new java.sql.Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("INSERT INTO postinfo VALUES(?,?)",index, post.text);
        jdbcTemplate.update("update communityinfo set postAmount = ? where postAmount = ?",index + 1,index);
        System.out.println("success");
    }
    public void AddComment(int postNumber, String comment)
    {
        int index = (int)jdbcTemplate.queryForMap("select commentAmount from communityinfo").get("commentAmount");
        jdbcTemplate.update("INSERT INTO commentinfo VALUES(?,?,?,?)",index,postNumber,comment,new java.sql.Timestamp(System.currentTimeMillis()));
        jdbcTemplate.update("update communityinfo set commentAmount = ? where commentAmount = ?",index + 1,index);
        System.out.println("success");
    } 
    public List<Title> GetPostList(int pageNumber)
    {
        List<Map<String, Object>> result =  jdbcTemplate.queryForList("select postNumber,postTitle,postWriter,uploadDate from postlist order by postNumber desc limit 10");
        List<Title> ret = new ArrayList<Title>();
        for (Map<String,Object> cur : result)
        {
            Title tmp = new Title();
            tmp.postNumber = (int)cur.get("postNumber");
            tmp.postTitle = (String)cur.get("postTitle");
            tmp.postWriter = (String)cur.get("postWriter");
            tmp.SetUploadDate(Timestamp.valueOf((LocalDateTime)cur.get("uploadDate")));
            ret.add(tmp);
        }
        return ret;
    }
    public String GetPassword(int postId)
    {
        return (String)(jdbcTemplate.queryForMap("select postPassword from postlist where postNumber = ?",postId).get("postPassword"));
    }
    public void DeletePost(int postNumber)
    {
        jdbcTemplate.execute("delete from postlist where postNumber = "+postNumber);
    }
    public List<Comment> GetCommentList(int postNumber)
    {
        List<Map<String, Object>> result =  jdbcTemplate.queryForList("select postNumber,comment,writeTime from commentinfo where postNumber = ?",postNumber);
        List<Comment> ret = new ArrayList<Comment>();
        for (Map<String,Object> cur : result)
        {
            Comment tmp = new Comment();
            tmp.postNumber = (int)cur.get("postNumber");
            tmp.comment = (String)cur.get("comment");
            tmp.SetUploadDate(Timestamp.valueOf((LocalDateTime)cur.get("writeTime")));
            ret.add(tmp);
        }
        return ret;
    }
    public Post GetPost(int postId)
    {
        Map<String, Object> result =  jdbcTemplate.queryForMap("select postlist.postTitle,postlist.postWriter,postlist.uploadDate,postinfo.postText from postlist join postinfo on postlist.postNumber = postinfo.postNumber where postlist.postNumber = ?",postId);
        Post ret = new Post();
        ret.title = (String) result.get("postTitle");
        ret.writer = (String) result.get("postWriter");
        ret.text = (String) result.get("postText");
        ret.SetUploadDate(Timestamp.valueOf((LocalDateTime)result.get("uploadDate")));
        return ret;
    }
    public void EditPost(Post post)
    {
        jdbcTemplate.execute("update postlist set postTitle = "+post.title+" where postNumber = "+post.number);
        jdbcTemplate.execute("update postinfo set postText = "+post.text+" where postNumber = "+post.number);
    }

}
