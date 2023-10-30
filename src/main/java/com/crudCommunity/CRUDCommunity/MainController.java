package com.crudCommunity.CRUDCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class MainController {

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
    public String save(@RequestParam("memberEmail") String memberEmail,
                       @RequestParam("memberPassword") String memberPassword,
                       @RequestParam("memberName") String memberName)
    {
        System.out.println("email : " + memberEmail);
        System.out.println("pwd : " + memberPassword);
        System.out.println("name : " + memberName);
        return "redirect:/main";
    }
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }


}
