package com.example.firstprojaect.controller;

import com.example.firstprojaect.dto.MemberForm;
import com.example.firstprojaect.entity.Member;
import com.example.firstprojaect.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@Slf4j
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
//        log.info(savedMember.toString());
        return "redirect:/members/"+savedMember.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "members/index";
    }

    @GetMapping("members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("members/update")
    public String update(MemberForm form){
        Member memberEntity = form.toEntity();
        log.info(memberEntity.toString());
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        log.info(target.toString());
        if(target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/"+memberEntity.getId();
    }

    @GetMapping("members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Member target = memberRepository.findById(id).orElse(null);
        if(target!=null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }
        return "redirect:/members";
    }

}
