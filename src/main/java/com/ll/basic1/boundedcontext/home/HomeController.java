package com.ll.basic1.boundedcontext.home;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/home")
public class HomeController {
    private static int count = 0;
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
        Person person = new Person(name, age);
        persons.put(person.id, person);
        return String.format("%d번 사람이 추가되었습니다.", person.id);
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

    @GetMapping("/modifyPerson")
    @ResponseBody
    public String modifyPerson(@RequestParam int id, @RequestParam String name, @RequestParam int age){
        if (persons.containsKey(id)) {
            persons.put(id, new Person(id,name,age));
            return String.format("%d번 사람이 수정되었습니다.", id);
        }
        return String.format("%d번 사람이 존재하지 않습니다.", id);
    }

    @GetMapping("/people")
    @ResponseBody
    public List<Person> getPeople(){
        return persons.values().stream().toList();
    }

    @GetMapping("/cookies/increase")
    @ResponseBody
    public int cookieIncrease(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            res.addCookie(new Cookie("count", "0"));
            return 0;
        }

        Integer count = Arrays
                .stream(cookies)
                .filter(c -> c.getName().equals("count"))
                .map(Cookie::getValue)
                .mapToInt(Integer::valueOf)
                .findFirst()
                .orElse(0);
        res.addCookie(new Cookie("count", String.valueOf(count+1)));
        return count + 1;
    }
}
