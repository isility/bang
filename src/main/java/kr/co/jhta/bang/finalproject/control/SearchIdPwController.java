package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.EmailService;
import kr.co.jhta.bang.finalproject.service.MemberService;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/search")
public class SearchIdPwController {

    @Autowired
    MemberService memberService;

    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    PaymentService payService;


    @GetMapping(value = {"/id", "/pw"})
    public String searchIdorPw(Principal principal, Model model) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }


        return "login/searchIdPw.html";
    }
    
    
    @PostMapping("/id")
    public String searchIdResult(HttpServletRequest request, Principal principal, Model model) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }


        ///////////

        String emailandName = request.getParameter("email_member_name");
        String emailandEmail = request.getParameter("email_member_email");
        String phoneandName = request.getParameter("phone_member_name");
        String phoneandPhone = request.getParameter("phone_member_phone");


        log.info("email : {} ", emailandName);
        log.info("email : {} ", emailandEmail);
        log.info("phone : {} ", phoneandName);
        log.info("phone : {} ", phoneandPhone);

        List<MemberDTO> emailList = memberService.findByIdEmail(emailandName, emailandEmail);

        log.info("ID 찾기 emailList : {} ", emailList);

        List<MemberDTO> phoneList = memberService.findByIdPhone(phoneandName, phoneandPhone);

        log.info("ID 찾기 phoneList : {} ", phoneList);


        if ((emailList != null && !emailList.isEmpty()) || (phoneList != null && !phoneList.isEmpty())) {
            if (emailList != null && !emailList.isEmpty()) {
                model.addAttribute("dtoList", emailList);

                // 이메일로 소셜 로그인 여부 확인 및 모델에 추가
                boolean isSocialLogin = false;
                for (MemberDTO dto : emailList) {
                    String id = dto.getMember_id();
                    if (id.startsWith("Kakao_") || id.startsWith("Google_") || id.startsWith("Naver_")) {
                        isSocialLogin = true;
                        model.addAttribute("isSocialLogin", true);
                        break;
                    }
                }
            }

            if (phoneList != null && !phoneList.isEmpty()) {
                model.addAttribute("dtoList", phoneList);

                // 전화번호로 소셜 로그인 여부 확인 및 모델에 추가
                boolean isSocialLogin = false;
                for (MemberDTO dto : phoneList) {
                    String id = dto.getMember_id();
                    if (id.startsWith("Kakao_") || id.startsWith("Google_") || id.startsWith("Naver_")) {
                        isSocialLogin = true;
                        model.addAttribute("isSocialLogin", true);
                        break;
                    }
                }
            }

            return "login/getId";
        } else if ((emailList == null || emailList.isEmpty()) && (phoneList == null || phoneList.isEmpty())) {

            return "login/findError";
        }

        return "login/findError";
    }

    @PostMapping("/pw")
    public String memberInfoCheck(@RequestParam("member_id") String id,
                                  @RequestParam("email_member_name") String name,
                                  @RequestParam("email_member_email") String email,
                                  Principal principal,
                                  Model model) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }


        ///////////

        int rusult = memberService.searchPwMemberInfoCheck(id, name, email);

        if(rusult == 1) {
            model.addAttribute("member_id", id);
            return "login/resetPw.html";
        } else {
            return "login/findError";
        }
    }


    @PostMapping("/resetPw")
    public String resetPw(Model model,
                          @RequestParam("member_id") String id,
                          @RequestParam("member_pw") String pw,
                          Principal principal) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }


        ///////////



        // 바꿀 패스워드를 인코딩
        String member_pw = passwordEncoder.encode(pw);

        log.info("비밀번호 바꾸려는 member id : {}", id);
        log.info("비밀번호 바꾸려는 member pw : {}", pw);
        log.info("비밀번호 바꾸려는 member pw 인코딩 : {}", member_pw);


        if (id != null && member_pw != null) {
            memberService.resetPw(id, member_pw);
            return "redirect:/login";
        } else {
            return "login/findError";
        }
    }







    @PostMapping("/searchDetailId")
    @ResponseBody
    public String searchDetailId(@RequestParam("member_id") String id,
                                 @RequestParam("member_email") String email) throws MessagingException, UnsupportedEncodingException {

        log.info("이메일 보낼 ID : {} ", id);
        log.info("이메일 보낼 메일주소 : {} ", email);

        try {
            String result = emailService.sendDetailIdEmail(id, email);
            log.info("이메일 보내기 result : {} ", result);
            return result;
        } catch (Exception e) {
            // 에러가 발생한 경우 클라이언트에게 에러 응답을 전달
            return "error";
        }
    }

}
