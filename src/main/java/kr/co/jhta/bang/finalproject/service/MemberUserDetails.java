package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MemberUserDetails implements UserDetails, OAuth2User {

    private MemberDTO memberDto;

    private Map<String, Object> attributes;

    public MemberUserDetails() {
        // 기본 생성자
    }

    public MemberUserDetails(MemberDTO memberDto) {
        this.memberDto = memberDto;
    }


    public MemberUserDetails(MemberDTO memberDto, Map<String, Object> attributes) {
        this.memberDto = memberDto;
        this.attributes = attributes;
    }



    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
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
            log.info("MemberUserDetails Collection 메서드 : " + authorities);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        log.info("MemberUserDetails pw : " + memberDto.getMember_pw());
        return memberDto.getMember_pw();
    }

    @Override
    public String getUsername() {
        log.info("MemberUserDetails id : " + memberDto.getMember_id());
        return memberDto.getMember_id();
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

    @Override
    public String getName() {
        return memberDto.getMember_name();
    }
}