package com.example.firstprojaect.dto;

import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.entity.Member;

public class MemberForm {
    private String email;
    private String password;

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return new Member(null, email, password);
    }


}
