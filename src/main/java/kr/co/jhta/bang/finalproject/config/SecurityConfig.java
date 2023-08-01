package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.service.MemberUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberUserDetailService memberDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 로그인 페이지는 모두 접근 가능하도록 허용
                                        // @RequestMapping(value = "/join") 하면 css 가 비정상적임 (보완 필요)
                .antMatchers("/join", "/costomerTerms", "/costomerJoinForm", "/businessOk", "/businessTerms", "/businessJoinForm", "/emailCheck", "/costomerJoinForm", "/businessJoinForm", "/idCheck", "/nicknameCheck", "/businessJoin", "/captcha").permitAll() // 회원가입 페이지는 모두 접근 가능하도록 허용
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("member_id")
                .passwordParameter("member_pw")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/sass/**"); // 정적 리소스는 보안 설정을 무시하도록 설정
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailService).passwordEncoder(passwordEncoder);
    }*/
}
