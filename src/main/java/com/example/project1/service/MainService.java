package com.example.project1.service;
import com.example.project1.repo.UsersRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

//Spring Boot에서 사용자 인증을 처리하는 서비스 클래스
//사용자가 로그인하면 UsersRepo를 이용하여
// 아이디와 비밀번호가 맞는지 확인하고, 그 결과에 따라 리다이렉트 URL(정보,주소)을 반환
@Service
@RequiredArgsConstructor
public class MainService {
//MainService는 비즈니스 로직을 처리하는 클래스
    private  final UsersRepo usersRepo;
    //UsersRepo를 주입받아 데이터베이스와 연동할 수 있도록 합니다
    private  final HttpSession httpSession; // 세션 추가


    public String login(
            String username,
            String password,
            HttpSession session,
            Model model
    ){

        Boolean check = usersRepo.existsByUsernameAndPassword(username,password);
        //로컬스토리지에 담기
        // 로그인시 파라미터 값을 확인후 결과를 리다이렉트로 보냄

        if (check) {
            session.setAttribute("username", username);
            model.addAttribute("msg","로그인 성공");
            return "redirect:/main";
        }else {
            model.addAttribute("msg","로그인 실패");
            return "/login";
        }
    }
}