package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String member_id = request.getParameter("username");
        String pw = request.getParameter("password");
        String member_pw = passwordEncoder.encode(pw);

        MemberDTO memberDto = memberService.selectMemberDetail(member_id);

        log.info("onAuthenticationSuccess ID: {}", member_id);
        log.info("onAuthenticationSuccess PW: {}", pw);
        log.info("onAuthenticationSuccess PW 인코딩: {}", member_pw);
        log.info("onAuthenticationSuccess memberDto: {}", memberDto);

        memberService.loginFailuresReset(member_id);

        response.sendRedirect("/");
    }
}
