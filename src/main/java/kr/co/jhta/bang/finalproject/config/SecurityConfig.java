package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.service.MemberOAuth2UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

@Configuration
@EnableWebSecurity
@Order(1) // 1번 처리 (기본 로그인)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    MemberOAuth2UserDetailService oAuth2DetailService;

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder);
    }

    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 로그인 페이지는 모두 접근 가능하도록 허용
                .antMatchers("/join/**").permitAll() // 회원가입 페이지는 모두 접근 가능하도록 허용
                .antMatchers("/search/**").permitAll() // id, pw 찾기 페이지는 모두 접근 가능하도록 허용
                .antMatchers("/mypage/**").access("hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/loginError")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                .and()
                    .authenticationProvider(customAuthenticationProvider)
                    .oauth2Login()
                    .loginPage("/login/oauth2/**")
                    .userInfoEndpoint()
                    .userService(oAuth2DetailService);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/sass/**", "/error"); // 정적 리소스는 보안 설정을 무시하도록 설정
    }

}
