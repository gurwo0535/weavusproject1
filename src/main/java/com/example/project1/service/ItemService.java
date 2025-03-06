package com.example.project1.service;

import com.example.project1.dto.ItemsDto;
import com.example.project1.dto.UsersDto;
import com.example.project1.entity.Items;
import com.example.project1.entity.Users;
import com.example.project1.repo.ItemRepo;
import com.example.project1.repo.UsersRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
//컨트롤러(MainController)에서 요청을 받으면
//ItemService가 비즈니스 로직을 수행하고 결과를 반환

    private final ItemRepo itemRepo;
    private  final UsersRepo usersRepo;
    public List<Items> findAll(HttpSession session, Model model) {
//        현재 로그인한 사용자의 아이템 조회

        Users users = checkLogin(session);
        // 현재 로그인한 사용자 정보(users)**를 가져옴
        String  msg = users.getUsername()+"님 환영합니다~";
        // 메세지 만듦
        try {
            model.addAttribute("welcome", msg);
            // 모델에 담아서 html에서 사용 할 수 있게 함
            return itemRepo.findByUserId(users.getId());
            // 현재 사용자의 아이템 리스트 조회
        }catch (Exception e){
            return List.of();
            //예외 발생 시 빈 리스트(List.of()) 반환하여 프로그램이 중단되지 않도록 처리
        }
    }

    public String write(ItemsDto itemsDto, HttpSession session, Model model) {
        //ItemsDto를 받아서 새로운 아이템을 생성 후 데이터베이스에 저장

        try {
            Items items = Items.builder()
                    .name(itemsDto.getName())
                    .description(itemsDto.getDescription())
                    .createdAt(LocalDate.now())
//                    .updatedAt(LocalDate.now())
                    .user(checkLogin(session))
                    // 로그인한 사용자 user 필드에 저장
                    .build();
            itemRepo.save(items);
            // 디비에 저장
            model.addAttribute("msg","글쓰기 성공");
            return "redirect:/main";
        }catch (Exception e){
            model.addAttribute("msg","글쓰기 실패");
            return "/write";

        }
    }

    public Items findItems(Integer id) {
        Items items = itemRepo.findById(id).orElse(null);
        //id를 기준으로 아이템 조회.
        //없으면 null 반환.
        return items;
    }

    public String edit(Integer id, ItemsDto itemsDto, Model model) {
        Items items = itemRepo.findById(id).orElse(null);
        //id를 기준으로 아이템 조회.
        //없으면 null 반환.


        // 수정 멘트 둘 다 안나옴
        if (items == null) {
            model.addAttribute("msg","수정 할 글이 없습니다.");
            return  "redirect:/main";
        }
        items.setName(itemsDto.getName());
        items.setDescription(itemsDto.getDescription());
        items.setUpdatedAt(itemsDto.getUpdatedAt());
        items.setUpdatedAt(LocalDate.now());
        itemRepo.save(items);
        //DB에 저장
        model.addAttribute("msg","수정 성공");
        return  "redirect:/main";
    }

    public void delete(Integer id) {
        try {
            itemRepo.deleteById(id);
            // id를 기준으로 삭제를 진행한다
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Users checkLogin(
            HttpSession session
    ){
        if (session.getAttribute("username") == null) {
            //session에서 "username" 가져오기.
            return null;
            //username이 없으면 null 반환 → 로그인되지 않은 상태.
        }

        Users users = usersRepo.findByUsername((String) session.getAttribute("username"));
        return users;
        //usersRepo.findByUsername(username)을 호출하여 해당 사용자 정보 반환.


    }
}
