package com.example.project1.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Lombok 라이브러리를 사용하여 자동으로 생성자, getter/setter, toString() 등을 생성
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // 엔티티 클래스
//이 클래스가 JPA 엔티티임을 나타냄 (즉, 데이터베이스의 users 테이블과 연결됨)
@Builder // Users 객체를 빌더 패턴으로 생성할 수 있도록 함
@AllArgsConstructor
// 모든 필드를 포함하는 생성자 자동 생성
@NoArgsConstructor
// 기본 생성자 자동 생성 (JPA에서는 기본 생성자가 필수)
@Data
//  getter/setter 및 객체 정보 출력 메서드를 자동으로 생성해 줌


public class Users {

    @Id // : 기본 키(PK) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    기본 키를 자동 증가(AUTO_INCREMENT) 방식으로 설정
//    데이터베이스가 자동으로 id 값을 증가시킴

    private int id;
    private String username;
    private String password;


}
