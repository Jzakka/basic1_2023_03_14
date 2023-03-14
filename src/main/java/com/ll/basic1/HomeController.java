package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static int count = 0;
    private static int id = 1;

    private static ArrayList<Person> persons = new ArrayList<>();

    @GetMapping("/main2")
    @ResponseBody
    public String  hello() {
        return "반가웠습니다.";
    }

    @GetMapping("/main3")
    @ResponseBody
    public String  hello2() {
        return "즐거웠습니다.";
    }

    @GetMapping("/increase")
    @ResponseBody
    public int  increase() {
        return count++;
    }

    @GetMapping("/plus")
    @ResponseBody
    public int plus(
            @RequestParam(defaultValue = "0") int a,
            int b) {
        return a+b;
    }

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(String name, int age){
        persons.add(new Person(id, name, age));
        return String.format("%d번 사람이 추가되었습니다.", id++);
    }

    @GetMapping("/people")
    @ResponseBody
    public ArrayList<Person> getPeople(){
        return persons;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Person{
        int id;
        String name;
        int age;
    }
}
