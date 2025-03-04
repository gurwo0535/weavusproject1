package com.example.project1.controller;

import com.example.project1.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class MainController {

    private  final MainService mainService;

    @GetMapping("/main")
    public String home(){
        return "/main";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";


    }
    @PostMapping("/login")
    public String loginPost(String username, String password)
    {

        return mainService.login(username,password);
        // return "redirect:/main";
    }

}