package com.example.firstprojaect.dto;

import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(null,email,password);
    }
}
