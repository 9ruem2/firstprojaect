package com.example.firstprojaect.repository;

import com.example.firstprojaect.entity.Article;
import com.example.firstprojaect.entity.Comment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동해 테스트하겠다는 선언
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticledId() {
//  Case 1: 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticledId(articleId);

            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a,b,c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"4번 글의 모든 댓글을 출력");
        }

//  Case 2: 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticledId(articleId);

            // 3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"1번 글은 댓글이 없음");
        }
//  Case 3: 9번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 9L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticledId(articleId);

            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"9번 게시글은 없음 따라서 댓글도 없음");
        }
//  Case 4: 999번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 999L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticledId(articleId);

            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"999번 게시글은 없음 따라서 댓글도 없음");
        }
//  Case 5: -1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = -1L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticledId(articleId);

            // 3. 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(),"-1번 게시글은 없음 따라서 댓글도 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        {
            //  Case1 : "Park"의 모든 댓글 조회
            // 1. 입력 데이터 준비
            String nickname = "Park";

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname, "조깅");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
        }

        //  Case2 : "Kim"의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "Kim";

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            Comment a = new Comment(2L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname, "아이 엠 샘");
            Comment b = new Comment(5L, new Article(5L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(6L, "당신의 취미는?", "댓글 고고고"), nickname, "유튜브 시청");

            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Kim의 모든 댓글을 출력!");
        }

        //  Case3 : null의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = null;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "null의 모든 댓글을 출력!");
        }
        //  Case4 : ""의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nickname = "";

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "\"\"의 모든 댓글을 출력!");
        }
    }
}