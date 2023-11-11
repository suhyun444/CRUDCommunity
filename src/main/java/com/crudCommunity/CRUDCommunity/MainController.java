package com.crudCommunity.CRUDCommunity;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
    @GetMapping("main")
    public String start()
    {
        return "index";
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
        return "redirect:/main";
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
        return "listview";
    }
    @ResponseBody
    @GetMapping("postlistinfo/{pageNumber}")
    public List<Title> GetPostListInfo(@PathVariable int pageNumber)
    {
        System.out.println("db open");
        List<Title> result = mainModel.GetPosts(pageNumber);
        for (int i=0;i<result.size();++i  )
        {
            System.out.println(result.get(i).postNumber);
            System.out.println(result.get(i).postWriter);
            System.out.println(result.get(i).postTitle);
            System.out.println(result.get(i).uploadDate);
        }
        return result;
    }

    @GetMapping("post/{postNumber}")
    public String GetPost(@PathVariable int postNumber)
    {
        return "redirect:/main";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }


}
