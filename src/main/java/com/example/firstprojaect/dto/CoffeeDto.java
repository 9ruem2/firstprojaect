package com.example.firstprojaect.dto;

import com.example.firstprojaect.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CoffeeDto {
    Long id;
    String name;
    String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
