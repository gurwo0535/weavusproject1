package com.example.project_store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    public String login(String id, String password){

        return "/main";
    }

}
