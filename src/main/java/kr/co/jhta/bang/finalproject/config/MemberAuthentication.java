package kr.co.jhta.bang.finalproject.config;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MemberAuthentication extends UsernamePasswordAuthenticationToken {

    private final MemberDTO memberDto;

    public MemberAuthentication(MemberDTO memberDto,
                                Object principal,
                                Object credentials,
                                Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.memberDto = memberDto;
    }

    public MemberDTO getMemberDTO() {
        return memberDto;
    }
}
