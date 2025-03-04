package com.example.project1.service;

import com.example.project1.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private  final UsersRepo usersRepo;

    public String login(String username, String password){
        Boolean check = usersRepo.existsByUsernameAndPassword(username,password);
        //로컬스토리지에 담기
        if (check) {
            return "redirect:/main";
        }else {
            return "redirect:/login";
        }
    }
}