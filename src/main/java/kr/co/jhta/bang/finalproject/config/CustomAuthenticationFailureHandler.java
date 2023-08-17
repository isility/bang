package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String member_id = request.getParameter("username");
        String pw = request.getParameter("password");
        String member_pw = passwordEncoder.encode(pw);

        MemberDTO memberDto = memberService.selectMemberDetail(member_id);

        log.info("onAuthenticationFailure ID: {}", member_id);
        log.info("onAuthenticationFailure PW: {}", pw);
        log.info("onAuthenticationFailure PW 인코딩: {}", member_pw);
        log.info("onAuthenticationFailure memberDto: {}", memberDto);

        if (memberDto == null) {
            log.info("ID 를 틀렸을 때");

            response.sendRedirect("/loginIdError");
        } else if (memberDto.getMember_loginFailures() < 5) {
            log.info("5번 미만 틀렸을 때");

            // 로그인 실패 횟수 업데이트
            memberService.loginFailures(member_id);

            // 업데이트된 로그인 실패 횟수 다시 로드
            memberDto = memberService.selectMemberDetail(member_id);

            // 세션에 업데이트된 로그인 실패 횟수 저장
            request.getSession().setAttribute("loginFailuresCount", memberDto.getMember_loginFailures());

            log.info("로그인 실패 횟수 : {} ", memberDto.getMember_loginFailures());

            // 로그인 실패 페이지로 이동
            response.sendRedirect("/loginError");

        } else {
            log.info("5번 이상 틀렸을 때");

            // 세션에 업데이트된 로그인 실패 횟수 저장
            request.getSession().setAttribute("loginFailuresCount", memberDto.getMember_loginFailures());

            log.info("로그인 실패 횟수 : {} ", memberDto.getMember_loginFailures());

            response.sendRedirect("/loginError");
        }
    }
}
