package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// security 설정에서 loginProcessingURL
// login 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc 되어있는 loadUserByUsername 실행
@Service
@Slf4j
public class MemberUserDetailService implements UserDetailsService {

    @Autowired
    MemberService memberService;


    @Override
    public MemberUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberUserDetailService username : " + username);

        MemberDTO memberDto = memberService.selectMemberDetail(username);

        if (memberDto != null) {
            log.info("MemberUserDetailService 사용자 정보 : {} ", memberDto);
        } else {
            log.info("사용자를 찾을 수 없습니다.");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        MemberUserDetails memberUserDetails = new MemberUserDetails(memberDto);

        return memberUserDetails;
    }
}