package kr.co.jhta.bang.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    PasswordEncoder passwordEncoder;


    // 인증 메서드
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String usernameQuery = "SELECT member_id as username, member_pw as password FROM member WHERE member_id = ? ";

        StringBuffer authQuery = new StringBuffer();

        authQuery.append("SELECT m.member_id as username, r.role_name as authority ");
        authQuery.append("FROM member m, role r ");
        authQuery.append("WHERE m.role_number = r.role_number and m.role_number = ? ");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usernameQuery)
                .authoritiesByUsernameQuery(authQuery.toString())
                .passwordEncoder(passwordEncoder);
    }


    // 정적 리소스의 요청을 지정하고, ignoring() 메서드를 통해 보안 설정을 무시하도록 설정
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    // 인가 메서드
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll() // 로그인 페이지는 모두 접근 가능하도록 허용
                .antMatchers("/index").authenticated() // "/index" 페이지는 인증된 사용자만 접근 가능
                .antMatchers("/seller-page").hasRole("SELLER") // "/seller-page" 페이지는 "SELLER" 역할이 있는 사용자만 접근 가능
                .antMatchers("/**").hasRole("USER") // 기타 모든 페이지는 "USER" 역할이 있는 사용자만 접근 가능


                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("member_id")
                .passwordParameter("member_pw")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index");
    }
}
