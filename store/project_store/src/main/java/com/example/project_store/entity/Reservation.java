package com.example.project_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity // 엔티티 클래스
//이 클래스가 JPA 엔티티임을 나타냄 (즉, 데이터베이스의 users 테이블과 연결됨)
@Builder // Users 객체를 빌더 패턴으로 생성할 수 있도록 함
@AllArgsConstructor
// 모든 필드를 포함하는 생성자 자동 생성
@NoArgsConstructor
// 기본 생성자 자동 생성 (JPA에서는 기본 생성자가 필수)
@Data
//  getter/setter 및 객체 정보 출력 메서드를 자동으로 생성해 줌


public class Reservation {

    @Id // : 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    기본 키를 자동 증가(AUTO_INCREMENT) 방식으로 설정
//    데이터베이스가 자동으로 id 값을 증가시킴

    private int id; // 예약 아이디
    private String storeName; // 예약하는 가게 명
    private LocalDate reseDay;// 예약 일자
    private String userid; // fk

    @JoinColumn(name = "user_id")
//    외래 키(FK) 컬럼을 user_id로 지정
//   (즉, items 테이블에는 user_id라는 컬럼이 생기며,
//   users 테이블의 id 값을 참조)

    @ManyToOne // 이거 맞음 ?

    private User user;
}
