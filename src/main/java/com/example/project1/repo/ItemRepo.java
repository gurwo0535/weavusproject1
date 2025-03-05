package com.example.project1.repo;

import com.example.project1.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Items, Integer> {
    List<Items> findByUserId(int userId);
}
