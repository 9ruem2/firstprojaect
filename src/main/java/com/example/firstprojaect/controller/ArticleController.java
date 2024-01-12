package com.example.firstprojaect.controller;

import com.example.firstprojaect.dto.ArticleForm;
import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j //로깅 기능을 위한 어노테이션 추가
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        ArrayList<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        return "articles/index";
    }
}
