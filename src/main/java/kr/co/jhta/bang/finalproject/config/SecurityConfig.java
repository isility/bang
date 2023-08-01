package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.service.MemberOAuth2UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    MemberOAuth2UserDetailService userDetailService;

    @Autowired
    PasswordEncoder passwordEncoder;




}
