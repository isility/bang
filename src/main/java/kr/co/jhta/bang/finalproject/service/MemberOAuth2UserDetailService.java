package kr.co.jhta.bang.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Slf4j
public class MemberOAuth2UserDetailService extends DefaultOAuth2UserService {

    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        String clientName = userRequest.getClientRegistration().getClientName();
        return null;
    }
}
