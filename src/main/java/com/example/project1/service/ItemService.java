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

    private final ItemRepo itemRepo;
    private  final UsersRepo usersRepo;
    public List<Items> findAll(HttpSession session, Model model) {
//        데이터베이스에서 모든 아이템을 조회

        Users users = checkLogin(session);
        String  msg = users.getUsername()+"님 환영합니다~";
        try {
            model.addAttribute("welcome", msg);
          return itemRepo.findByUserId(users.getId());
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
                    .build();
            itemRepo.save(items);
            model.addAttribute("msg","글쓰기 성공");
            return "redirect:/main";
        }catch (Exception e){
            model.addAttribute("msg","글쓰기 실패");
            return "/write";

        }
    }

    public Items findItems(Integer id) {
        Items items = itemRepo.findById(id).orElse(null);
        return items;
    }

    public String edit(Integer id, ItemsDto itemsDto, Model model) {
        Items items = itemRepo.findById(id).orElse(null);

        if (items == null) {
            model.addAttribute("msg","수정 할 글이 없습니다.");
            return  "redirect:/main";
        }
        items.setName(itemsDto.getName());
        items.setDescription(itemsDto.getDescription());
        items.setUpdatedAt(itemsDto.getUpdatedAt());
        items.setUpdatedAt(LocalDate.now());
        itemRepo.save(items);
        model.addAttribute("msg","수정 성공");
        return  "redirect:/main";
    }

    public void delete(Integer id) {
        try {
            itemRepo.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Users checkLogin(
            HttpSession session
    ){
        if (session.getAttribute("username") == null) {
            return null;
        }
        Users users = usersRepo.findByUsername((String) session.getAttribute("username"));
        return users;
    }
}
