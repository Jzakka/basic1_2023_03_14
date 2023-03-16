package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.Rq;
import com.ll.basic1.common.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/login")
    public String showLogin() {
        return "usr/member/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public RsData login(String username, String password) {
        if (username == null || password == null) {
            return RsData.result("F-3", "Username 과 password를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            rq.setSession("user", username);
        }

        return rsData;
    }

    @GetMapping("/logout")
    @ResponseBody
    public RsData logout() {
        boolean removed = rq.removeSession("user");

        if (removed) {
            return RsData.result("S-1", "로그아웃 되었습니다.");
        }
        return RsData.result("F-1", "이미 로그아웃 상태입니다.");
    }

    @GetMapping("/me")
    @ResponseBody
    public RsData me() {
        String username = rq.getSession("user", "anonymous");

        if (username.equals("anonymous")) {
            return RsData.result("F-1", "로그인 후 이용해주세요");
        }

        return RsData.result("S-1", String.format("당신의 username은 %s입니다.", username));
    }
}
