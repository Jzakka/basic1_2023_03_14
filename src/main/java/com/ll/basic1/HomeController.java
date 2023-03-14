package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static int count = 0;
    private static int id = 1;
    private static Map<Integer, Person> persons = new HashMap<>();

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
        persons.put(id, new Person(id, name, age));
        return String.format("%d번 사람이 추가되었습니다.", id++);
    }

    @GetMapping("/removePerson")
    @ResponseBody
    public String removePerson(@RequestParam int id){
        if (persons.containsKey(id)) {
            persons.remove(id);
            return String.format("%d번 사람이 삭제되었습니다.", id);
        }
        return String.format("%d번 사람이 존재하지 않습니다.", id);
    }

    @GetMapping("/people")
    @ResponseBody
    public List<Person> getPeople(){
        return persons.values().stream().toList();
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
