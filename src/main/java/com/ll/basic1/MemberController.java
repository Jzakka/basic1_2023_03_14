package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private static ArrayList< Member> members = new ArrayList<>();

    {
        members.add(new Member("user1", "1234"));
    }

    @GetMapping("/login")
    public Map<String,String> login(String username, String password){
        Map<String, String> result = new HashMap<>();

        Member findMember = members
                .stream()
                .filter(member -> member.name.equals(username))
                .findAny()
                .orElse(null);

        if (findMember == null) {
            result.put("resultCode", "F-2");
            result.put("msg", String.format("%s는 존재하지 않는 회원입니다.", username));
        } else {
            if (findMember.password.equals(password)) {
                result.put("resultCode", "S-1");
                result.put("msg", String.format("%s 님 환영합니다.", username));
            } else {
                result.put("resultCode", "F-1");
                result.put("msg", "비밀번호가 일치하지 않습니다.");
            }
        }
        return result;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Member{
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
}
