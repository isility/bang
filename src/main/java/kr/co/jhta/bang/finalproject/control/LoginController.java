package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @Autowired
    MemberService memberService;

    @Autowired
    PaymentService payService;


    @GetMapping("/login")
    public String login(Principal principal, Model model) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }

        return "login/login.html";
    }


    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberDTO login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        log.info("id : {} ", loginId);
        log.info("pw : {} ", password);

        // 회원 정보 및 비밀번호 체크, 맞다면 리턴
        MemberDTO memberDto = memberService.login(loginId, password);

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (memberDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", memberDto);
            session.setMaxInactiveInterval(60 * 30);

            // 로그인 성공 시 MemberDTO를 JSON으로 반환하여 클라이언트에게 전달
            return memberDto;
        } else {
            // 로그인 실패 시 null을 반환하여 클라이언트에게 전달
            return null;
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "login/login.html";

    }


    @GetMapping("/loginError")
    public String loginError(HttpServletRequest request, Model model) {

        int loginFailuresCount = (int) request.getSession().getAttribute("loginFailuresCount");

        log.info("loginError loginFailuresCount : {} ", loginFailuresCount);

        model.addAttribute("loginFailuresCount", loginFailuresCount);

        return "login/loginError.html";
    }

    @GetMapping("/loginIdError")
    public String loginIdError() {
        return "login/loginIdError.html";
    }


    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "login/accessDenied.html";
    }
}