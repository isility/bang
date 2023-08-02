package kr.co.jhta.bang.finalproject.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminMemberController {

    @GetMapping("/memberList")
    public String getMemberList(){
        log.info(">>>>>>>>move to memberList<<<<<<<< ");
        return "/admin/memberList";
    }
}
