package com.example.project1.controller;

import com.example.project1.dto.ItemsDto;
import com.example.project1.service.ItemService;
import com.example.project1.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class MainController {

    private  final MainService mainService;
    private final ItemService itemService;
    @GetMapping("/main")
    public String home(Model model){
        model.addAttribute("items", itemService.findAll());
        return "/main";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";

    }
    @GetMapping("/write")
    public String write(){
        return "/write";

    }
    @PostMapping("/write")
    public String writePost(
            @ModelAttribute ItemsDto itemsDto
    ){
        return itemService.write(itemsDto);
    }

    @PostMapping("/login")
    public String loginPost(String username, String password)
    {

        return mainService.login(username,password);
        // return "redirect:/main";
    }

}