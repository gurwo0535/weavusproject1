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
        //items에는 Items 객체들이 리스트 형태로 저장됨.
        //itemService.findAll()에서 데이터를 가져와 컨트롤러로 전달하고,
        //이후 model.addAttribute("items", items);을 통해 뷰로 넘김.

        model.addAttribute("items", items);
        //템플릿(main.html)에서 items를 사용할 수 있도록 추가.

        return "/main"; // main.html 뷰를 반환
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
            //사용자가 입력한 게시글 데이터를 itemsDto 객체로 받아옴.
            HttpSession session,
            Model model){
        return itemService.write(itemsDto,session, model);
        // 호출하여 글을 저장.
    }

    @PostMapping("/login")
    public String loginPost(
            @RequestParam String username,
            @RequestParam String password,
            // @RequestParam을 사용해 사용자가 입력한 username과 password를 받아옴.

            HttpSession session,
            Model model
            ) {

        return mainService.login(username,password,session, model);
        //mainService.login()을 호출해 로그인 처리.

        // return "redirect:/main"; // 원래는 이거
    }

    @GetMapping("/edit/{id}")
    //주소창에 아이디가 적혀있을때
    public String edit(@PathVariable Integer id,
                       // URL에 포함된 {id} 값을 받아옴.
                       Model model){
        model.addAttribute("item",itemService.findItems(id));
        //호출해 해당 ID의 게시글 정보를 가져와 model에 저장.
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(
            @PathVariable Integer id,
            // 수정할 게시글의 ID를 URL에서 추출.
            @ModelAttribute ItemsDto itemsDto,
            ////사용자가 입력한 수정된 데이터.
            Model model
    ){
        return  itemService.edit(id,itemsDto,model);
        //호출하여 수정
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable Integer id
            //삭제할 게시글의 ID를 URL에서 추출.
    ){
        itemService.delete(id);
        //호출해 해당 게시글 삭제.
        return  "redirect:/main";
    }
}