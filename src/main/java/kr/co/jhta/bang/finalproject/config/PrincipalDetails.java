package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// security session 에 들어갈 객체
@Slf4j
public class PrincipalDetails implements UserDetails {

    private MemberDTO memberDto;

    public PrincipalDetails(MemberDTO memberDto) {
        this.memberDto = memberDto;
    }


    // 해당 user의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (memberDto != null) {
            String authority = null;
            if (memberDto.getRole_number() == 0) {
                authority = "ROLE_ADMIN";
            } else if (memberDto.getRole_number() == 1) {
                authority = "ROLE_USER";
            } else if (memberDto.getRole_number() == 2) {
                authority = "ROLE_SELLER";
            }
            authorities.add(new SimpleGrantedAuthority(authority));
            log.info("Collection 메서드 : " + authorities);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        log.info("pw : " + memberDto.getMember_pw());
        return memberDto.getMember_pw();
    }

    @Override
    public String getUsername() {
        log.info("username : " + memberDto.getMember_name());
        return memberDto.getMember_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
