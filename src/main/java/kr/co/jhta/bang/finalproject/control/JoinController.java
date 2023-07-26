package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class JoinController {

    @Autowired
    MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "join/join.html";
    }

    @GetMapping("/costomerTerms")
    public String terms() {
        return "join/costomerTerms.html";
    }

    @GetMapping("/costomerJoinForm")
    public String costomerJoinForm() {
        return "join/costomerJoinForm.html";
    }

    @PostMapping("/costomerJoinForm")
    public String costomerJoinOk(@ModelAttribute MemberDTO memberDto,
                                 @ModelAttribute PersonDTO personDto,
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck) {

        log.info("일반 회원 가입 dto :" + memberDto + personDto);
        log.info("이메일 수신 여부 : " + emailCheck);

        if(idCheck(memberDto.getMember_id()) == 1) {
            // id가 이미 존재할 때
            
            return "join/costomerJoinForm.html";
        } else if (idCheck(memberDto.getMember_id()) == 0) {
            // id가 신규일 때
            
            memberService.insertMemberOne(memberDto);
            memberService.insertPersonOne(personDto);

            return "login/login.html";
        }
        return "redirect:index.html";
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public int idCheck(@RequestParam("member_id")String memberId) {
        return memberService.idCheck(memberId);
    }
}
