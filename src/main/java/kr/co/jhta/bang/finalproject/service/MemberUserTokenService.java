package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberUserTokenService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60l;

    public String loginToken(String username, String password) {
        log.info("jwt 발행");
        return JwtUtil.createJwt(username, secretKey, expiredMs);
    }
}
