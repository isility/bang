package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.service.MemberUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

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
        String auth = memberUserDetails.getAuthorities().toString();


        // db의 패스워드
        MemberUserDetails user = (MemberUserDetails) userDetailsService.loadUserByUsername(username);
        String dbPassword = user.getPassword();

        log.info("CustomAuthenticationProvider 의 username : {}", username);
        log.info("CustomAuthenticationProvider 의 pw : {}", password);
        log.info("CustomAuthenticationProvider 의 passwordEncoder : {}", inputPasswordEncoder);
        log.info("CustomAuthenticationProvider 의 dbPassword : {}", dbPassword);
        log.info("CustomAuthenticationProvider 의 auth : {}", auth);

        // DB 에서의 사용자 정보와 일치하는지 비교, 아니라면 예외
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.info("기존 사용자 정보와 불일치");
            throw new BadCredentialsException(username);
        }

        return new UsernamePasswordAuthenticationToken(username, inputPasswordEncoder, memberUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
