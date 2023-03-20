package com.ll.basic1.boundedcontext.article;

import com.ll.basic1.boundedcontext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
