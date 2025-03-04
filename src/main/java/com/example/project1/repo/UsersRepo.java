package com.example.project1.repo;

import com.example.project1.entity.Items;
import com.example.project1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsersRepo extends JpaRepository<Users, Integer> {


    Boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
