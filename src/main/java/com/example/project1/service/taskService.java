package com.example.project1.service;

import com.example.project1.dto.UsersDto;
import com.example.project1.entity.Users;
import com.example.project1.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//사용자 회원가입(signUp) 기능을 담당하는 서비스 클래스입니다.
//사용자가 입력한 정보()를 검증한 후, 새로운 엔티티를 데이터베이스에 저장


@Service
@RequiredArgsConstructor
public class taskService {
    private final UsersRepo userRepo;
    @Transactional
    // 트랜잭션을 보장하여 회원가입 중 오류 발생 시 자동 롤백
    public String signUp(UsersDto dto){
        //클라이언트가 전달한 회원가입 정보 (DTO) 담겨있다

        try {
            if (userRepo.existsByUsername(dto.getUsername())){
                return "아이디 중복 되었습니다.";
                // userRepo를 통해 user테이블의 중복을 확인하는 작업
            }
            if (!dto.getPassword().equals(dto.getPassword2())){
                // 불일치 할 경우
                return "비밀번호가 일치하지않습니다.";
            }


        Users users = Users.builder()
            .username(dto.getUsername())
            .password(dto.getPassword())
            .build();
        userRepo.save(users);
//            데이터베이스에 저장 (userRepo.save(users))
        return "true";
//            회원가입 성공 시 반환"true"

        }catch (Exception e){
            return "회원가입 실패";

        }
    };
}
