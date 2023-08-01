package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login/login.html";
    }

    @PostMapping("/login")
    public String loginOk() {



        return null;
    }
}
