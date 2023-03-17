package com.ll.basic1.boundedcontext.article;

import com.ll.basic1.boundedcontext.article.entity.Article;
import com.ll.basic1.common.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(String title, String body) {
        if (title == null || title.isBlank() || title.isEmpty()) {
            return RsData.result("F-1", "제목을 입려하세요");
        }

        if (body == null || body.isBlank() || body.isEmpty()) {
            return RsData.result("F-1", "내용을 입려하세요");
        }

        Article article = articleService.write(title, body);

        return RsData.result("S-1", "Created article no.1", article);
    }
}
