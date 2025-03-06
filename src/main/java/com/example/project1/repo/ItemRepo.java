package com.example.project1.repo;

import com.example.project1.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Items, Integer> {
//    T = 엔티티 클래스 (Items)
//    ID = 기본 키(PK) 타입 (Integer)
    //JPA Repository를 사용하여 Items 엔티티와 데이터베이스를 연결하는 역할을 합니다
//    JpaRepository<Items, Integer>	Items 엔티티의 기본 CRUD 제공

    List<Items> findByUserId(int userId);
//    findByUserId(int userId)	특정 사용자의 아이템 목록 조회
//    사용자 ID(userId)를 기준으로 아이템 리스트를 조회하는 메서드.
//    Spring Data JPA의 메서드 네이밍 규칙을 따름 → findBy[필드명]
//    userId를 기준으로 Items 엔티티에서 해당 사용자의 아이템 리스트 반환.

}






