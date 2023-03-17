package com.ll.basic1.boundedcontext.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member{
    private int id;
    private String username;
    private String password;

    private static int lastId = 0;

    public Member(String name, String password) {
        this.id = ++lastId;
        this.username = name;
        this.password = password;
    }
}
