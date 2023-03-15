package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Person{
    int id;
    String name;
    int age;

    private static int lastId = 0;

    public Person(String name, int age) {
        this.id = ++lastId;
        this.name = name;
        this.age = age;
    }
}
