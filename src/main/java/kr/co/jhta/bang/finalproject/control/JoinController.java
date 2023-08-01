package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import kr.co.jhta.bang.finalproject.service.EmailService;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
public class JoinController {

    @Autowired
    MemberService memberService;
    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;


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
    public String businessOk() { return "join/businessOk.html"; }

    @PostMapping("/businessOk")
    public String businessNumber(@RequestParam("bzNum")String bzNum,
                               HttpSession session) {
        /* 사업자 번호를 여기로 저장 */
        session.setAttribute("bzNum", bzNum);
        log.info("businessOk 의 session에 담긴 bzNum : " + session.getAttribute("bzNum"));
        return "join/businessTerms.html";
    }


    @GetMapping("/businessTerms")
    public String businessTerms(HttpSession session) {
        return "join/businessTerms.html";
    }

    @PostMapping("/businessTerms")
    public String businessNumberForm(HttpSession session) {
        session.setAttribute("bzNum", session.getAttribute("bzNum"));
        log.info("businessTerms 의 session에 담긴 값 : " + session.getAttribute("bzNum"));
        return "join/businessJoinForm.html";
    }




    @GetMapping("/businessJoinForm")
    public String businessJoinForm(HttpSession session) {
        log.info("businessJoinForm 의 session에 담긴 값 : " + session.getAttribute("bzNum"));
        return "join/businessJoinForm.html";
    }


    @ResponseBody
    @PostMapping("/emailCheck")
    public String emailCheck(@RequestParam("member_email")String email,
                             @RequestParam("member_id")String id) throws MessagingException, UnsupportedEncodingException {
        return emailService.sendEmail(id, email);
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
            memberDto.setMember_pw(passwordEncoder.encode(memberDto.getMember_pw()));
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
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck,
                                 HttpSession session) {

        log.info("사업자 회원 가입 dto :" + memberDto + companyDto);
        log.info("이메일 수신 여부 : " + emailCheck);
        log.info("businessJoinForm 의 session 에 담긴 값 : " + (String) session.getAttribute("bzNum"));

        if(idCheck(memberDto.getMember_id()) == 1) {
            // id 또는 닉네임이 이미 존재할 때

            return "join/businessJoinForm.html";
        } else if (idCheck(memberDto.getMember_id()) == 0) {
            // id가 신규일 때
            memberDto.setMember_pw(passwordEncoder.encode(memberDto.getMember_pw()));
            memberDto.setMember_type(2);
            memberDto.setRole_number(2);
            companyDto.setCompany_registnumber((String) session.getAttribute("bzNum"));
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


    @PostMapping("/businessJoin")
    @ResponseBody
    public String bsApiSession() {
        /* 사업자 등록번호 조회 사이트 키 */
        return "F%2FG4iLSBelahsfdbMRRMHGkoBcbiEv6gKL%2Fm4SBhhu5g1IxczQEGoDodzsnYADgtSkijqfSERiTFSMNN6RTDTw%3D%3D";
    }


    @PostMapping("/captcha")
    @ResponseBody
    public String captcha() {
        return "6LeuiWgnAAAAAJ_EghIJxPCem8QToRF4VIj7TfLg";
    }
}
