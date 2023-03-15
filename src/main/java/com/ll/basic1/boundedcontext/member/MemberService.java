package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemberService {
    private static ArrayList<Member> members = new ArrayList<>();

    {
        members.add(new Member("user1", "1234"));
    }

    public RsData login(String username, String password){
        RsData res;
        Member findMember = members
                .stream()
                .filter(member -> member.name.equals(username))
                .findAny()
                .orElse(null);

        if (findMember == null) {
            res = RsData.result("F-2", String.format("%s는 존재하지 않는 회원입니다.", username));
        } else {
            if (findMember.password.equals(password)) {
                res = RsData.result("S-1", String.format("%s 님 환영합니다.", username));
            } else {
                res = RsData.result("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }

        return res;
    }
}
