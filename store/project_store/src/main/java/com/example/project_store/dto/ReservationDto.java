package com.example.project_store.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
public class ReservationDto {

    private int id; // 예약 아이디
    private String storeName; // 예약하는 가게 명
    private LocalDate reseDay;// 예약 일자
    private String userid; // fk


}
