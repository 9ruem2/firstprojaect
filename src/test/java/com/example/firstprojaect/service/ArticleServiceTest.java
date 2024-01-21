package com.example.firstprojaect.service;

import com.example.firstprojaect.dto.ArticleForm;
import com.example.firstprojaect.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 예상 데이터
        Article a = new Article(1L, "가가가가","1111");
        Article b = new Article(2L, "나나나나","2222");
        Article c = new Article(3L, "다다다다","3333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        // 실제 데이터
        List<Article> articles = articleService.index();

        // 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    @Transactional
    void show_성공_존재하는_id_입력() {
        Long id = 1L;

        // 1. 예상 데이터
        Article expected = new Article(id,"가가가가","1111");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;
        // 1. 예상데이터
        Article expected = null;

        // 2. 실제데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검사
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공_title_content만_있는_dto_입력() {
        // 1. 예상데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title, content);
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        // 예상데이터
        Article expected = null;

        Article article = articleService.create(dto);

        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content_있는_dto_입력() {
        Long id = 3L;
        String title = "가가가가";
        String content = "1111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        Article article = articleService.update(id,dto);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        Long id = 3L;
        String title = "변경했어요";
        String content = null;
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id,"변경했어요","3333");

        Article article = articleService.update(id,dto);

        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력() {
        Long id = 4L;
        String title = "aaaa";
        String content = "11111";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        Article article = articleService.update(id, dto);

        assertEquals(expected, article);
    }
    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        Long id = 3L;
        String title = "다다다다";
        String content = "3333";
        Article expected = new Article(id, title, content);

        Article article = articleService.delete(id);

        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id_입력() {
        Long id = 4L;
        Article expected = null;

        Article article = articleService.delete(id);

        assertEquals(expected, article);

    }



}