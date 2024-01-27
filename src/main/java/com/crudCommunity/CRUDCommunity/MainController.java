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

    @GetMapping("post/{postNumber}")
    public String GetPostPage(@PathVariable int postNumber)
    {
        return "post";
    }
    @GetMapping("edit/{postNumber}")
    public String EditPostPage(@PathVariable int postNumber)
    {
        return "edit";
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
    @GetMapping("postlistinfo/{pageNumber}")
    public ResponseEntity<List<Title>> GetPostListInfo(@PathVariable int pageNumber)
    {
        System.out.println("db open");
        List<Title> result = mainModel.GetPostList(pageNumber);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("postinfo/{postId}")
    public ResponseEntity<Post> GetPostInfo(@PathVariable int postId)
    {
        System.out.println("db open");
        Post result = mainModel.GetPost(postId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("commentinfo/{postId}")
    public ResponseEntity<List<Comment>> GetCommentInfo(@PathVariable int postId)
    {
        System.out.println("db open");
        List<Comment> result = mainModel.GetCommentList(postId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("getpassword/{postId}")
    public ResponseEntity<String> GetPassword(@PathVariable int postId)
    {
        System.out.println("getpassword");
        //return mainModel.GetPassword(postId);
        String result = mainModel.GetPassword(postId);
        System.out.println(result);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("writecomment")
    public ResponseEntity<Comment> WriteComment(@RequestBody Comment comment)
    {
        mainModel.AddComment(comment.postNumber,comment.comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
    @PostMapping("deletepost")
    public ResponseEntity<Integer> DeletePost(@RequestParam int postNumber)
    {
        mainModel.DeletePost(postNumber);
        return new ResponseEntity<>(postNumber,HttpStatus.OK); 
    }
    
    @PostMapping("addpost")
    public ResponseEntity<Post> write(@RequestBody Post post) {
        System.out.println("write");
        mainModel.AddPost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PostMapping("editpost")
    public ResponseEntity<Post> EditPost(@RequestBody Post post)
    {
        mainModel.EditPost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }



}
