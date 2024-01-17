package com.example.firstprojaect.api;

import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.repository.ArticleRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        log.info(articleRepository.findById(id).orElse(null).toString());
        return articleRepository.findById(id).orElse(null);
    }
}
