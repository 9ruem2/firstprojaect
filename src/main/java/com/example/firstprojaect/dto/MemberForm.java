package com.example.firstprojaect.dto;

import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(id,email,password);
    }
}
