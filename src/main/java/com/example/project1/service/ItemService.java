package com.example.project1.service;

import com.example.project1.dto.ItemsDto;
import com.example.project1.entity.Items;
import com.example.project1.repo.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepo itemRepo;
    public List<Items> findAll() {
        try {
          return itemRepo.findAll();
        }catch (Exception e){
            return List.of();
        }
    }

    public String write(ItemsDto itemsDto) {
        try {
            Items items = Items.builder()
                    .name(itemsDto.getName())
                    .description(itemsDto.getDescription())
                    .createdAt(LocalDate.now())
                    .user()
                    .build();

            itemRepo.save(items);
            return "글쓰기 성공";
        }catch (Exception e){
            return "글쓰기 실패";

        }
    }
}
