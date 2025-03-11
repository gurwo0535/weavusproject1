package com.example.project_store.service;

import com.example.project_store.dto.ReservationDto;
import com.example.project_store.dto.StorerDto;
import com.example.project_store.entity.Reservation;
import com.example.project_store.repo.ReservationRepo;
import com.example.project_store.repo.StorerRepo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private  final StorerRepo storerRepo;

    public String reservationUp(ReservationDto reservationDto) {


        Reservation reservation = Reservation.builder()
                .reseDay(reservationDto.getReseDay())
                .storeName(reservationDto.getStoreName())
//                .user(checkLogin(session))
                .build();
        reservationRepo.save(reservation);
        return "식당 예약에 성공";

    }

}
