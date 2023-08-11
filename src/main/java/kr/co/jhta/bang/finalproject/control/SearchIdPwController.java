package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.EmailService;
import kr.co.jhta.bang.finalproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/search")
public class SearchIdPwController {

    @Autowired
    MemberService memberService;

    @Autowired
    EmailService emailService;

    @GetMapping(value = {"/id", "/pw"})
    public String searchIdorPw() {
        return "login/searchIdPw.html";
    }
    
    
    @PostMapping("/id")
    public String searchIdResult(HttpServletRequest request, Model model) {

        String emailandName = request.getParameter("email_member_name");
        String emailandEmail = request.getParameter("email_member_email");
        String phoneandName = request.getParameter("phone_member_name");
        String phoneandPhone = request.getParameter("phone_member_phone");


        log.info("email : {} ", emailandName);
        log.info("email : {} ", emailandEmail);
        log.info("phone : {} ", phoneandName);
        log.info("phone : {} ", phoneandPhone);

        List<MemberDTO> emailList = memberService.findByIdEmail(emailandName, emailandEmail);
        List<MemberDTO> phoneList = memberService.findByIdPhone(phoneandName, phoneandPhone);


        if ((emailList != null && !emailList.isEmpty()) || (phoneList != null && !phoneList.isEmpty())) {
            
            // 이메일로 찾기
            // id, 이메일이 있는 지 찾아보고 (MemberDTO 리턴)
            model.addAttribute("dtoList", emailList);
            

            // 소셜 로그인 여부 확인
            boolean isSocialLogin = false;

            for (MemberDTO dto : emailList) {
                String id = dto.getMember_id();

                if (id.startsWith("Kakao_") || id.startsWith("Google_") || id.startsWith("Naver_")) {
                    isSocialLogin = true;
                    model.addAttribute("isSocialLogin", true);
                } else {
                    model.addAttribute("isSocialLogin", false);
                }
            }

            // 전화번호로 찾기
            // id, 이메일이 있는 지 찾아보고 (MemberDTO 리턴)
            model.addAttribute("dtoList", phoneList);
            
            
            // 소셜 로그인 여부 확인
            for (MemberDTO dto : phoneList) {
                String id = dto.getMember_id();

                if (id.startsWith("Kakao_") || id.startsWith("Google_") || id.startsWith("Naver_")) {
                    isSocialLogin = true;
                    model.addAttribute("isSocialLogin", true);
                } else {
                    model.addAttribute("isSocialLogin", false);
                }
            }


            return "login/getId";

        } else if ((emailList == null || emailList.isEmpty()) && (phoneList == null || phoneList.isEmpty())) {

            return "login/findError";
        }

        return "login/findError";
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


    @PostMapping("/searchDetailId")
    @ResponseBody
    public String searchDetailId(@RequestParam("member_id") String id,
                                 @RequestParam("member_email") String email) throws MessagingException, UnsupportedEncodingException {

        try {
            String result = emailService.sendDetailIdEmail(id, email);
            log.info("result : {} ", result);
            return result;
        } catch (Exception e) {
            // 에러가 발생한 경우 클라이언트에게 에러 응답을 전달
            return "error";
        }
    }



}
