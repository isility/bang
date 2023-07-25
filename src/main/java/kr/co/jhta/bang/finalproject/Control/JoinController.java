package kr.co.jhta.bang.finalproject.Control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
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

    @GetMapping("/costomerterms")
    public String terms() {
        return "join/costomerterms.html";
    }

    @GetMapping("/costomerjoinForm")
    public String costomerjoinForm() {
        return "join/costomerjoinForm.html";
    }

    @PostMapping("/costomerjoinOk")
    public String costomerjoinOk(@ModelAttribute MemberDTO dto) {

        log.info("일반 회원 가입 dto :" + dto);

        int result = memberService.idCheck(dto);

        if(result == 1) {
            // 이미 등록된 id 라면
            return "redirect:join/costomerjoinForm.html";
        } else if (result == 0) {
            // 신규 id 라면
            memberService.insertOne(dto);
            return "redirect:login";
        } else {
            return "redirect:login";
        }
    }

    @PostMapping("/idCheck")
    public int idCheck(@ModelAttribute MemberDTO dto) {
        // 아이디 중복검사
        int result = memberService.idCheck(dto);

        log.info("아이디 중복 체크 : " + result);

        return result;
    }
}
