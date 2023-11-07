package com.crudCommunity.CRUDCommunity;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@Controller
@SpringBootApplication
public class MainController {

    @Resource(name = "mainModel")
    MainModel mainModel;
    @GetMapping("/main")
    public String start()
    {
        return "index.html";
    }
	@GetMapping("/write")
    public String writeForm()
    {
        return "write.html";
    }
    @PostMapping("/write")
    public String write(@RequestParam("postWriter") String postWriter,
                       @RequestParam("postPassword") String postPassword,
                       @RequestParam("postTitle") String postTitle,
                       @RequestParam("postText") String postText)
    {
        System.out.println("write");
        mainModel.AddPost(postWriter,postPassword,postTitle,postText);
        return "redirect:/main";
    }
    @GetMapping("/list/{postNumber}")
    public String GetPost(@PathVariable int postNumber)
    {
        return "redirect:/main";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }


}
