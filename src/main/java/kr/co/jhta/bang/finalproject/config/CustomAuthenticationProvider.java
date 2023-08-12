package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import kr.co.jhta.bang.finalproject.service.MemberUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MemberService memberService;


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MemberUserDetails memberUserDetails;

    @Autowired
    PasswordEncoder passwordEncoder;

    @SuppressWarnings("unchecked")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        // 유저가 입력한 비번을 인코딩한 값
        String inputPasswordEncoder = passwordEncoder.encode(password);

        // db의 패스워드
        MemberUserDetails user = (MemberUserDetails) userDetailsService.loadUserByUsername(username);
        String dbPassword = user.getPassword();


        // 유저 권한 설정하기
        MemberDTO memberDto = memberService.selectMemberDetail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        String authority = null;
        if (memberDto.getRole_number() == 0) {
            authority = "ROLE_ADMIN";
        } else if (memberDto.getRole_number() == 1) {
            authority = "ROLE_USER";
        } else if (memberDto.getRole_number() == 2) {
            authority = "ROLE_SELLER";
        }
        authorities.add(new SimpleGrantedAuthority(authority));

        // 테스트
        log.info("CustomAuthenticationProvider 의 username : {}", username);
        log.info("CustomAuthenticationProvider 의 pw : {}", password);
        log.info("CustomAuthenticationProvider 의 passwordEncoder : {}", inputPasswordEncoder);
        log.info("CustomAuthenticationProvider 의 dbPassword : {}", dbPassword);
        log.info("CustomAuthenticationProvider 의 auth : {}", authorities);

        // DB 에서의 사용자 정보와 일치하는지 비교, 아니라면 예외
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.info("기존 사용자 정보와 불일치");
            throw new BadCredentialsException(username);
        }

        return new UsernamePasswordAuthenticationToken(username, inputPasswordEncoder, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}