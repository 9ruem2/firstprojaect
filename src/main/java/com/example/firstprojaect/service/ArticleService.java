package com.example.firstprojaect.service;

import com.example.firstprojaect.dto.ArticleForm;
import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        articleList.stream().forEach(article -> articleRepository.save(article));
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        return articleList;
    }
}
