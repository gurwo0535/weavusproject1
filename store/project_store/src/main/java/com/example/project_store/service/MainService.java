package com.example.project_store.service;

import com.example.project_store.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class MainService {


    private final UserRepo userRepo;

    private  final HttpSession session;


    public String login(
            String id,
            String password,
            HttpSession session,
            Model model
            ){

        boolean check = userRepo.existsByIdAndPassword(id,password);

        if (check) {
            session.setAttribute("id",id);
            model.addAttribute("msg","로그인에 성공");
            // 작동하지않음 페이지는 잘 넘아감
            return "redirect:/main";
//            return "/main";

        }
//        else if (check) {
//            equals(String id)
//        } // 아이디 패스워드중 값을 입력받지않았을경우에 사용하고 싶음
        else {
            model.addAttribute("msg","로그인에 실패");
            return "/login";
        }
    }

}
