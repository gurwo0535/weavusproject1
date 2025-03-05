package com.example.project1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
// 데이터베이스의 items 테이블과 매핑
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id // 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 자동 증가 설정

    private int id;
    private String name;
    private  String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @JoinColumn(name = "user_id")
//    외래 키(FK) 컬럼을 user_id로 지정
//   (즉, items 테이블에는 user_id라는 컬럼이 생기며,
//   users 테이블의 id 값을 참조)

    @ManyToOne
//    아이템은 하나의 사용자와 연관됨
//    (사용자는 여러 개의 아이템을 가질 수 있음)

    private Users user;



}
