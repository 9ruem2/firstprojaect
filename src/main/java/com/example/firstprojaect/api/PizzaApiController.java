package com.example.firstprojaect.api;

import com.example.firstprojaect.dto.PizzaDto;
import com.example.firstprojaect.entity.Pizza;
import com.example.firstprojaect.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/pizzas")
    public ResponseEntity<List<PizzaDto>> index(){
        return null;
    }

    @GetMapping("/pizzas/{id}")
    public ResponseEntity<PizzaDto> show(@PathVariable Long id){
        return null;
    }

    @PostMapping("/pizzas")
    public ResponseEntity<PizzaDto> creat(PizzaDto dto){
        return null;
    }

    @DeleteMapping("/pizzas/{id}")
    public ResponseEntity<PizzaDto> delet(@PathVariable Long id){
        return null;
    }
}
