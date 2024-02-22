package com.crudCommunity.CRUDCommunity;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
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
    public String EditPostPage(@PathVariable int postNumber, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("editPermission"+postNumber) == null || !(Boolean)session.getAttribute("editPermission"+postNumber))
            return "denied";
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
    public ResponseEntity<List<Post>> GetPostListInfo(@PathVariable int pageNumber)
    {
        System.out.println("db open");
        List<Post> result = mainModel.GetPostList(pageNumber);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("postinfo/{postId}")
    public ResponseEntity<Post> GetPostInfo(@PathVariable Long postId)
    {
        Post result = mainModel.GetPost(postId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("commentinfo/{postId}")
    public ResponseEntity<List<Comment>> GetCommentInfo(@PathVariable Long postId)
    {
        List<Comment> result = mainModel.GetCommentList(postId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("getpassword/{postId}")
    public ResponseEntity<String> GetPassword(@PathVariable Long postId)
    {
        String result = mainModel.GetPassword(postId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @GetMapping("getpostamount")
    public ResponseEntity<Long> GetPostAmount()
    {
        return new ResponseEntity<>(mainModel.GetPostCount(),HttpStatus.OK);
    }
    @PostMapping("writecomment")
    public ResponseEntity<Comment> WriteComment(@RequestBody Comment comment)
    {
        mainModel.AddComment(comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }
    @PostMapping("deletepost")
    public ResponseEntity<Long> DeletePost(@RequestParam Long postNumber)
    {
        mainModel.DeletePost(postNumber);
        return new ResponseEntity<>(postNumber,HttpStatus.OK); 
    }
    
    @PostMapping("addpost")
    public ResponseEntity<Post> write(@RequestBody Post post) {
        mainModel.AddPost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @PostMapping("editpost")
    public ResponseEntity<Post> EditPost(@RequestBody Post post)
    {
        mainModel.EditPost(post);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @PostMapping("checkpassword")
    public ResponseEntity<Boolean> CheckPassword(@RequestBody Post post, HttpServletRequest request)
    {
        Boolean result = false;
        String password = mainModel.GetPassword(post.number);
        System.out.println(password + " , " + post.password + "      compare");
        if(password.equals(post.password))
        {
            HttpSession session = request.getSession();
            session.setAttribute("editPermission"+post.number, true);
            result = true;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }



}
