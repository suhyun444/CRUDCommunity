package com.crudCommunity.CRUDCommunity;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@GetMapping("/save")
    public String saveForm()
    {
        return "save.html";
    }
    @PostMapping("/save")
    public String save(@RequestParam("postWriter") String postWriter,
                       @RequestParam("postPassword") String postPassword,
                       @RequestParam("postTitle") String postTitle,
                       @RequestParam("postText") String postText)
    {
        System.out.println("save");
        mainModel.AddPost(postWriter,postPassword,postTitle,postText);
        return "redirect:/main";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }


}
