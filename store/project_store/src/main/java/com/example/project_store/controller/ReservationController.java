package com.example.project_store.controller;

import com.example.project_store.entity.Storer;
import com.example.project_store.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 컨트롤러를 사용한다
@RequiredArgsConstructor   // 서비스단과 연결 시켜준다
public class ReservationController {


    @GetMapping("/reservation")
    public String showReservationPage(@RequestParam("id") Long storeId, Model model) {
        model.addAttribute("msg1", "예약 정보를 입력하세요.");
        model.addAttribute("storeId", storeId); // 선택한 식당 ID를 전달
        return "reservation";
    }
}

