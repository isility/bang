package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/search")
public class SearchIdPwController {


    @GetMapping(value = {"/id", "/pw"})
    public String searchIdorPw() {
        return "login/searchIdPw.html";
    }
    
    
    @PostMapping("/id")
    /*@ResponseBody*/
    public String searchIdResult() {
        
        // 가져온 값이 이메일 == 값 있음, 이름 ==null, 전화번호 == null
        // id, 이메일이 있는 지 찾아보고 (MemberDTO 리턴)
        // 있으면 *** 처리해서 이메일 리턴
        // 있는데 소셜 아이디라면 소셜 아이디라고 리턴
        // 없으면 null 리턴

        // 가져온 값이 이메일 == null, 이름 + 전화번호 == 값 있음
        // 이름+전화번호에 따른 id+이메일이 있는 지 찾아보고 (MemberDTO 리턴)
        // 있으면 *** 처리해서 이메일 리턴
        // 있는데 소셜 아이디라면 소셜 아이디라고 리턴
        // 없으면 null 리턴
        
        
        
        return "login/getId.html";
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
