package com.example.project1.repo;

import com.example.project1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

//UsersRepo는 Users 테이블과 연결된 데이터베이스 접근 레이어 역할을 합니다.
// 기본적인 CRUD 기능은 JpaRepository가 자동으로 제공하며,
// 추가적으로 existsByUsernameAndPassword()와 existsByUsername()을 구현하여
// 로그인 및 회원가입 검증 기능을 수행할 수 있습니다.

public interface UsersRepo extends JpaRepository<Users, Integer> {

// JpaRepository를 확장하여 기본적인 CRUD 기능을 제공합니다.
// UsersRepo는 JpaRepository<Users, Integer>를 상속받은 인터페이스
// Users 엔티티와 연결된 테이블을 관리합니다.
//Integer는 Users 엔티티의 기본 키(ID)의 타입을 의미합니다.

    Boolean existsByUsernameAndPassword(String username, String password);
//    특정 username과 password가 존재하는지 확인하는 메서드입니다.
//    사용자의 로그인 검증 시 활용될 수 있습니다.
    boolean existsByUsername(String username);

//    username이 이미 존재하는지 확인하는 메서드입니다.
//    회원가입 시 중복 검사할 때 사용될 가능성이 큽니다.

    Users findByUsername(String username);
}
