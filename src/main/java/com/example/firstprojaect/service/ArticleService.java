package com.example.firstprojaect.service;

import com.example.firstprojaect.dto.ArticleForm;
import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article articleEntity=dto.toEntity();
        log.info("id: {}, article: {}", id, articleEntity.toString());
        Article target = articleRepository.findById(id).orElse(null);
        if(target==null || id != articleEntity.getId()){
            log.info("잘못된 요청! id: {}, article:{}", id, articleEntity.toString());
            return null;
        }
        target.patch(articleEntity);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target==null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }
}
