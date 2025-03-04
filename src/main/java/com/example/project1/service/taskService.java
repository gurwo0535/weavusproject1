package com.example.project1.service;

import com.example.project1.dto.UsersDto;
import com.example.project1.entity.Users;
import com.example.project1.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class taskService {
    private final UsersRepo userRepo;
    @Transactional
    public String signUp(UsersDto dto){

        try {
            if (userRepo.existsByUsername(dto.getUsername())){
                return "아이디 중복 되었습니다.";
            }
            if (!dto.getPassword().equals(dto.getPassword2())){
                return "비밀번호가 일치하지않습니다.";
            }


        Users users = Users.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .build();
        userRepo.save(users);
        return "true";

        }catch (Exception e){
            return "회원가입 실패";

        }
    };
}
