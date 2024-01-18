package com.example.firstprojaect.api;

import com.example.firstprojaect.dto.CoffeeDto;
import com.example.firstprojaect.entity.Coffee;
import com.example.firstprojaect.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberApiController {
    @Autowired
    CoffeeRepository coffeeRepository;

    // 커피 전체조회
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }

    // 커피 단건 조회
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }

    // 커피 생성
    @PostMapping("/api/coffees")
    public Coffee create(CoffeeDto dto){
        Coffee coffee = dto.toEntity();
        return coffeeRepository.save(coffee);
    }

    // 커피 수정
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null || id != coffee.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffee.patch(coffee);
        Coffee updated = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 커피 삭제
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
