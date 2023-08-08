package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class MemberOAuth2UserDetailService extends DefaultOAuth2UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2User userRequest : " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();
        int platformtypeNumber = 0;

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> map = oAuth2User.getAttributes();
        Set<String> s = map.keySet();
        Iterator<String> it = s.iterator();

        while(it.hasNext()) {
            String name = it.next();
            log.info(name + " : " + map.get(name));
        }

        String email = "";
        String name = "";

        if(clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
            name = oAuth2User.getAttribute("name");
            platformtypeNumber = 3;

            log.info("구글 이메일 :" + email);
            log.info("구글 등록 이름 :" + name);

            // DB 저장
            saveMember(clientName, email, name, platformtypeNumber);

            return super.loadUser(userRequest);
            
        } else if(clientName.equals("Naver")) {
            Map<String, Object> map2 = oAuth2User.getAttributes();
            // 구글은 키값을 바로 가져올 수 있는데
            // 네이버는 response 를 JSON 타입으로 한번더 전달하기 때문에 아래 과정 추가
            Map<String, Object> map3 = (Map<String, Object>)map2.get("response");

            email = (String) map3.get("email");
            name = (String) map3.get("name");
            platformtypeNumber = 1;

            log.info("네이버 이메일 :" + email);
            log.info("네이버 등록 이름 :" + name);

            // DB 저장
            saveMember(clientName, email, name, platformtypeNumber);

            return super.loadUser(userRequest);

        } else if(clientName.equals("Kakao")) {
            Map<String, Object> map2 = oAuth2User.getAttributes();

            Map<String, Object> map3 = (Map<String, Object>)map2.get("kakao_account");
            email = (String) map3.get("email");

            Map<String, Object> map4 = (Map<String, Object>)map2.get("properties");
            name = (String)map4.get("nickname");
            
            platformtypeNumber = 2;

            log.info("카카오 이메일 :" + email);
            log.info("카카오 등록 이름 :" + name);

            // DB 저장
            saveMember(clientName, email, name, platformtypeNumber);

            return super.loadUser(userRequest);

        } else {
            return null;
        }
    }


    private void saveMember(String clientName, String email, String name, int platformtypeNumber) {

        // DB 에 기존 회원이 있는 지 조회
        int result = memberService.idSocialCheck(clientName + "_" + email);

        // 등록되어 있지 않으면 DB 에 추가
        if(result == 0) {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String nowFormat = formatter.format(now);
            MemberDTO MemberDto = MemberDTO.builder()
                            .member_id(clientName + "_" + email)
                            .member_pw(passwordEncoder.encode("1234"))
                            .member_email(email)
                            .member_name(name)
                            .member_insertdate(nowFormat)
                            .role_number(1)
                            .grade_number(0)
                            .platformtype_number(platformtypeNumber)
                            .build();

            memberService.insertMemberOne(MemberDto);


            PersonDTO personDto = PersonDTO.builder()
                            .member_id(clientName + "_" + email)
                            .person_nickname(name)
                            .build();

            memberService.insertPersonOne(personDto);
        } else {
            log.info("기존 회원이 있음");
        }

    }
}
