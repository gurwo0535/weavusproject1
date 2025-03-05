package com.example.project1.controller;

import com.example.project1.dto.ItemsDto;
import com.example.project1.entity.Items;
import com.example.project1.service.ItemService;
import com.example.project1.service.MainService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class MainController {

    private  final MainService mainService;
    // 서비스 단과 열결하기 위함
    private final ItemService itemService;
    @GetMapping("/main") //매핑을 받는다
    public String home(
            Model model,
            HttpSession session
            ){
        List<Items> items = itemService.findAll(session,model);
        model.addAttribute("items", items);

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
            @ModelAttribute ItemsDto itemsDto,
            HttpSession session,
            Model model){
        return itemService.write(itemsDto,session, model);
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
            ) {

        return mainService.login(username,password,session, model);
        // return "redirect:/main";
    }


    @GetMapping("/edit/{id}")
    //주소창에 아이디가 적혀있을때
    public String edit(@PathVariable Integer id,
                       Model model){
        model.addAttribute("item",itemService.findItems(id));
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(
            @PathVariable Integer id,
            @ModelAttribute ItemsDto itemsDto,
            Model model
    ){
        return  itemService.edit(id,itemsDto,model);
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id
    ){
        itemService.delete(id);
        return  "redirect:/main";
    }
}