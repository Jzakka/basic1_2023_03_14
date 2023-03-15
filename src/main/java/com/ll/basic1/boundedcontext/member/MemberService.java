package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public RsData login(String username, String password){
        RsData res;
        Member findMember = memberRepository.findOne(username);

        if (findMember == null) {
            res = RsData.result("F-2", String.format("%s는 존재하지 않는 회원입니다.", username));
        } else {
            if (findMember.getPassword().equals(password)) {
                res = RsData.result("S-1", String.format("%s 님 환영합니다.", username));
            } else {
                res = RsData.result("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }

        return res;
    }
}
