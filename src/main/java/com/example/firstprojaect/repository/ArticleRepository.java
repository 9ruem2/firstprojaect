package com.example.firstprojaect.repository;

import com.example.firstprojaect.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {
}
