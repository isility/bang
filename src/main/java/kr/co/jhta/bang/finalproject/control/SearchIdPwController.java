package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/search")
public class SearchIdPwController {

    @Autowired
    MemberService memberService;



    @GetMapping(value = {"/id", "/pw"})
    public String searchIdorPw() {
        return "login/searchIdPw.html";
    }
    
    
    @PostMapping("/id")
    public String searchIdResult(HttpServletRequest request, Model model) {

        String email = request.getParameter("member_email");
        String name = request.getParameter("member_name");
        String phone = request.getParameter("member_phone");


        log.info("email : {} ", email);
        log.info("name : {} ", name);
        log.info("phone : {} ", phone);

        List<MemberDTO> dtoList = new ArrayList<>();

        // 이메일로 찾기 : 가져온 값이 이메일 == 값 있음, 이름 ==null, 전화번호 == null
        if (email != null && name == null && phone == null ) {
            // id, 이메일이 있는 지 찾아보고 (MemberDTO 리턴)
            model.addAttribute("dtolist",memberService.findByidEmail(email));

            // 있는데 소셜 아이디라면 소셜 아이디라고 리턴

            return "login/getId";

        } else if (email == null && name == null && phone == null) {

            return "login/getId";
        }
        // 없으면 null 리턴

        // 가져온 값이 이메일 == null, 이름 + 전화번호 == 값 있음
        // 이름+전화번호에 따른 id+이메일이 있는 지 찾아보고 (MemberDTO 리턴)
        // 있는데 소셜 아이디라면 소셜 아이디라고 리턴
        // 없으면 null 리턴

        return "login/getId";
    }

    @PostMapping("/pw")
    /*@ResponseBody*/
    public String searchPwResult() {

        // 가져온 값이 이메일 == 값 있음, 이름 ==null, 전화번호 == null
        // id, 이메일이 있는 지 찾아보고 (MemberDTO 리턴)
        // 있으면 *** 처리해서 이메일 리턴
        // 있는데 소셜 아이디라면 소셜 아이디라고 리턴
        // 없으면 null 리턴


        return "login/resetPw.html";
    }

}
