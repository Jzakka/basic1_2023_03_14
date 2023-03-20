package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        if (member == null) {
            return RsData.result("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!member.getPassword().equals(password)) {
            return RsData.result("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return RsData.result("S-1", "%s 님 환영합니다.".formatted(username), member);
    }

    public Member findById(long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member join(String username, String password){
        Member member = Member.builder()
                .username(username)
                .password(password)
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember;
    }
}
