package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.MemberService;
import kr.co.jhta.bang.finalproject.service.MemberUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/login")
    public String loginOk(@RequestParam("member_id")String id,
                          HttpServletRequest request) {


        // 사용자 인증을 수행합니다.
        UserDetails authentication = memberDetailService.loadUserByUsername(id);

        // 인증에 성공한 경우 사용자 정보를 세션에 저장합니다.
        // UserDetails 객체에 값이 있는지 확인
        if (authentication != null) {
            // HttpSession 객체를 얻어옴
            HttpSession session = request.getSession(true);

            // UserDetails 객체에 있는 id와 pw 값을 얻음
            id = authentication.getUsername();
            String pw = authentication.getPassword();

            // 세션에 id와 pw를 저장
            session.setAttribute("userId", id);
            session.setAttribute("userPassword", pw);

            log.info("로그인 성공");
            return "redirect:index";
        } else {
            log.info("로그인 실패");
            return "login/login.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "login/login.html";

    }

}
