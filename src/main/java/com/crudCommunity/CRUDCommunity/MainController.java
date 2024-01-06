package com.crudCommunity.CRUDCommunity;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SpringBootApplication
public class MainController {

    @Resource(name = "mainModel")
    MainModel mainModel;
    @GetMapping("")
    public String start()
    {
        return "redirect:postlist/0";
    }
	@GetMapping("write")
    public String writeForm()
    {
        return "write";
    }
    @PostMapping("write")
    public String write(@RequestParam("postWriter") String postWriter,
                       @RequestParam("postPassword") String postPassword,
                       @RequestParam("postTitle") String postTitle,
                       @RequestParam("postText") String postText)
    {
        System.out.println("write");
        mainModel.AddPost(postWriter,postPassword,postTitle,postText);
        return "redirect:postlist/0";
    }
    @GetMapping("postlist")
    public String GetPostList()
    {
        return "redirect:postlist/0";
    }
    @GetMapping("postlist/{pageNumber}")
    public String GetPostListPage(@PathVariable int pageNumber)
    {
        System.out.println("postlist open");
        return "list";
    }
    @ResponseBody
    @GetMapping("postlistinfo/{pageNumber}")
    public List<Title> GetPostListInfo(@PathVariable int pageNumber)
    {
        System.out.println("db open");
        List<Title> result = mainModel.GetPostList(pageNumber);
        return result;
    }
    @ResponseBody
    @GetMapping("postinfo/{postId}")
    public Post GetPostInfo(@PathVariable int postId)
    {
        System.out.println("db open");
        Post result = mainModel.GetPost(postId);
        return result;
    }

    @GetMapping("post/{postNumber}")
    public String GetPost(@PathVariable int postNumber)
    {
        return "post";
    }
    @PostMapping("writecomment")
    public ResponseEntity<Comment> WriteComment(@RequestBody Comment comment)
    {
        mainModel.AddComment(comment.postNumber,comment.comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }



}
