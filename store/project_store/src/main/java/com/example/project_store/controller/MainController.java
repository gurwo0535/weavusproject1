package com.example.project_store.controller;


import com.example.project_store.dto.ReservationDto;
import com.example.project_store.dto.StorerDto;
import com.example.project_store.dto.UserDto;
import com.example.project_store.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.project_store.service.MainService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class MainController {
    private final MainService mainService;
    private final ReservationService reservationService;



    @GetMapping("/main")
    public  String home(
    ){
        return "/main";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }



    @PostMapping("/login")
    public String loginPost(
            @RequestParam String id,
            @RequestParam String password,
            // @RequestParam을 사용해 사용자가 입력한 username과 password를 받아옴.
            HttpSession session,
            Model model
    ) {

        return mainService.login(id,password,session, model);
        //mainService.login()을 호출해 로그인 처리.

        // return "redirect:/main"; // 원래는 이거
    }

    @GetMapping("/myPage")
    public String showMyPage(){
        return "/myPage";
    }

    @PostMapping("/myPage")
    public String myPage(
            @ModelAttribute ReservationDto reservationDto,
            Model model
    ) {
        String msg = reservationService.reservationUp(reservationDto);
        if (msg.equals("식당 예약에 성공")) {
            return "redirect:/myPage";
        }
        model.addAttribute("msg",msg);
        return "/reservation";
    }


}
