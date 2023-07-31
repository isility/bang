package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import kr.co.jhta.bang.finalproject.service.EmailService;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
public class JoinController {

    @Autowired
    MemberService memberService;
    @Autowired
    EmailService emailService;


    @GetMapping("/join")
    public String join() {
        return "join/join.html";
    }


    @GetMapping("/costomerTerms")
    public String costomerTerms() {
        return "join/costomerTerms.html";
    }

    @GetMapping("/costomerJoinForm")
    public String costomerJoinForm() { return "join/costomerJoinForm.html"; }

    @GetMapping("/businessOk")
    public String businessOk() {

        return "join/businessOk.html";
    }

    @GetMapping("/businessTerms")
    public String businessTerms() {

        return "join/businessTerms.html";
    }

    @GetMapping("/businessJoinForm")
    public String businessJoinForm() { return "join/businessJoinForm.html"; }


    @ResponseBody
    @PostMapping("/emailCheck")
    public String emailCheck(@RequestParam("member_email")String email) throws MessagingException, UnsupportedEncodingException {
        return emailService.sendEmail(email);
    }




    @PostMapping("/costomerJoinForm")
    public String costomerJoinOk(@ModelAttribute MemberDTO memberDto,
                                 @ModelAttribute PersonDTO personDto,
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck) {

        log.info("일반 회원 가입 dto :" + memberDto + personDto);
        log.info("이메일 수신 여부 : " + emailCheck);

        if(idCheck(memberDto.getMember_id()) == 1 || nicknameCheck(personDto.getPerson_nickname()) == 1 ) {
            // id 또는 닉네임이 이미 존재할 때

            return "join/costomerJoinForm.html";
        } else if (idCheck(memberDto.getMember_id()) == 0) {
            // id가 신규일 때
            memberDto.setMember_type(1);
            memberDto.setRole_number(1);
            memberService.insertMemberOne(memberDto);
            memberService.insertPersonOne(personDto);

            return "login/login.html";
        }
        return "redirect:index.html";
    }




    @PostMapping("/businessJoinForm")
    public String businessJoinOk(@ModelAttribute MemberDTO memberDto,
                                 @ModelAttribute CompanyDTO companyDto,
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck) {

        log.info("사업자 회원 가입 dto :" + memberDto + companyDto);
        log.info("이메일 수신 여부 : " + emailCheck);

        if(idCheck(memberDto.getMember_id()) == 1) {
            // id 또는 닉네임이 이미 존재할 때

            return "join/businessJoinForm.html";
        } else if (idCheck(memberDto.getMember_id()) == 0) {
            // id가 신규일 때
            memberDto.setMember_type(2);
            memberDto.setRole_number(2);
            memberService.insertMemberOne(memberDto);
            memberService.insertCompanyOne(companyDto);

            return "login/login.html";
        }
        return "redirect:index.html";
    }



    @ResponseBody
    @PostMapping("/idCheck")
    public int idCheck(@RequestParam("member_id")String memberId) {
        return memberService.idCheck(memberId);
    }


    @ResponseBody
    @PostMapping("/nicknameCheck")
    public int nicknameCheck(@RequestParam("nickname")String nickname) {
        return memberService.nicknameCheck(nickname);
    }
}
