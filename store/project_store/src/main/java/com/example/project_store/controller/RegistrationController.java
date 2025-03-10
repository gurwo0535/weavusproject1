package com.example.project_store.controller;

import com.example.project_store.dto.StorerDto;
import com.example.project_store.entity.Storer;
import com.example.project_store.service.MainService;
import com.example.project_store.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class RegistrationController {
    private final RegistrationService registrationService;

    private  final MainService mainService; // ?

    @GetMapping("/storeRegistration")
    public String StoreRegistration(){
        return "/storeRegistration";
    }

//    @GetMapping("/storeList")
//    public String storeList(){
//        return "/storeList";
//    }

    @GetMapping("/storeList")
    public String storeList(
            Model model,
            HttpSession session
    ) {
        List<Storer> storers = registrationService.findAll(model,session);

        model.addAttribute("storers", storers);
        //템플릿(storers.html)에서 storers를 사용할 수 있도록 추가.

        return "/storeList";
    }



    @PostMapping("/storeRegistration")
    public String StorePost(
            @ModelAttribute StorerDto storerDto,
            Model model,
            HttpSession session
    ) {
        String msg = registrationService.registrationUp(storerDto,session);
        if (msg.equals("ture")) {
            return "redirect:/storeList";
        }
        model.addAttribute("msg", msg);
        return "/storeRegistration";

    }
}
