package com.example.project1.repo;

import com.example.project1.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Items, Integer> {
}
