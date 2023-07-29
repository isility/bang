package kr.co.jhta.bang.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private String authRandomCode;

    public void createCode() {
        Random random = new Random();

        String code1 = random.nextInt(10)+"";
        String code2 = random.nextInt(10)+"";
        String code3 = random.nextInt(10)+"";
        String code4 = random.nextInt(10)+"";
        String code5 = random.nextInt(10)+"";
        String code6 = random.nextInt(10)+"";

        authRandomCode = code1 + code2 + code3 + code4 + code5 + code6;
    }

    public String sendEmail(String to) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

        createCode();

        helper.setTo(to);
        helper.setFrom(new InternetAddress("bang.oops.official@gmail.com", "BANG & OOPS 고객센터"));
        helper.setSubject("BANG & OOPS 본인확인 인증번호 입니다.");
        helper.setText("test 인증번호 : " + authRandomCode, true);

        javaMailSender.send(message);

        log.info("서비스 단의 인증번호 : " + authRandomCode);

        return authRandomCode;
    }
}
