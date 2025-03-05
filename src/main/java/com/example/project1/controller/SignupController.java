package com.example.project1.controller;

import com.example.project1.dto.UsersDto;
import com.example.project1.service.taskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor

public class SignupController {

    private  final com.example.project1.service.taskService taskService;
    @GetMapping("/signup")
    public String getSignup(){
        return "/signup";
    }

    @PostMapping("/signup")
    private  String postSignup(
            @ModelAttribute UsersDto dto,
            Model model
            ){
        String msg = taskService.signUp(dto);
        if (msg.equals("ture")) {
            return "redirect:/login";
        }
        model.addAttribute("msg1",msg);
        return "/signup";
    }
}
