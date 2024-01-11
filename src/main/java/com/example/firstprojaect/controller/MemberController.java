package com.example.firstprojaect.controller;

import com.example.firstprojaect.dto.MemberForm;
import com.example.firstprojaect.entity.Member;
import com.example.firstprojaect.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup/new")
    public String newSignup(){
        return "members/new";
    }

    @PostMapping("/signup/join")
    public String createUser(MemberForm memberForm){
        Member member = memberForm.toEntity();
        Member savedMember = memberRepository.save(member);
        return "";
    }
}
