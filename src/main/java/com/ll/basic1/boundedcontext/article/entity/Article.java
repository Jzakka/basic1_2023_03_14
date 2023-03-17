package com.ll.basic1.boundedcontext.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
public class Article {

    @Id //PK
    @GeneratedValue(strategy = IDENTITY) //AUTO_INCREMENT
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
