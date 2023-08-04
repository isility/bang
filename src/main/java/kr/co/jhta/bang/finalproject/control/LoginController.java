package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.MemberService;
import kr.co.jhta.bang.finalproject.service.MemberUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberUserDetailService memberDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login/login.html";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "login/login.html";

    }

}
