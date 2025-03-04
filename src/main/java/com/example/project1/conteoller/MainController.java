package com.example.project1.conteoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.midi.ShortMessage;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(){
        return "/main";
    }

}