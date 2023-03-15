package com.ll.basic1.boundedcontext.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member{
    int id;
    String name;
    String password;

    private static int lastId = 0;

    public Member(String name, String password) {
        this.id = ++lastId;
        this.name = name;
        this.password = password;
    }
}
