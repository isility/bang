package kr.co.jhta.bang.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberUserDetailService implements UserDetailsService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보 조회 쿼리
        String usernameQuery = "SELECT member_id, member_pw FROM member WHERE member_id = ?";
        // 사용자의 권한 정보 조회 쿼리
        String userAuthQuery = "SELECT r.role_name FROM member m, role r WHERE m.role_number = r.role_number and member_id = ?";

        // 사용자 이름을 파라미터 값으로 전달하기 위해 배열에 넣기
        Object[] params = new Object[]{username};
        List<GrantedAuthority> authorities = new ArrayList<>(); // 사용자의 권한 정보를 담을 리스트

        // 사용자 정보 조회 쿼리 실행 및 결과 처리
        List<Map<String, Object>> userInfo = jdbcTemplate.queryForList(usernameQuery, params);
        if (userInfo.isEmpty()) {
            log.info("로그인 실패");
        }

        // 사용자의 권한 정보 조회 쿼리 실행 및 결과 처리
        List<Map<String, Object>> authRows = jdbcTemplate.queryForList(userAuthQuery, params);
        for (Map<String, Object> authRow : authRows) {
            authorities.add(new SimpleGrantedAuthority(userAuthQuery)); // 사용자의 권한을 리스트에 추가
        }

        // 사용자 정보와 권한 정보를 이용하여 UserDetails 객체 생성
        String memberId = (String) userInfo.get(0).get("member_id");
        String password = (String) userInfo.get(0).get("member_pw");
        boolean enabled = true;

        return new User(memberId, password, enabled, true, true, true, authorities);

    }

}
