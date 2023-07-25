package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/costomerJoinOk")
    public String costomerJoinOk(@ModelAttribute MemberDTO memberDto,
                                 @ModelAttribute PersonDTO personDto) {

        log.info("일반 회원 가입 dto :" + memberDto + personDto);


        memberService.insertMemberOne(memberDto);
        memberService.insertPersonOne(personDto);

        return "login/login.html";

    }

    @PostMapping("/idCheck")
    public int idCheck(@ModelAttribute MemberDTO dto) {
        // 아이디 중복검사
        int result = memberService.idCheck(dto);

        log.info("아이디 중복 체크 : " + result);

        return result;
    }
}
