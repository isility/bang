package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
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
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping(value = "/join")
public class JoinController {

    @Autowired
    MemberService memberService;
    @Autowired
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PaymentService payService;;


    @GetMapping()
    public String join(Principal principal, Model model) {

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

        return "join/join.html";
    }




    @GetMapping("/costomerTerms")
    public String costomerTerms(Principal principal, Model model) {

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

        return "join/costomerTerms.html";
    }

    @GetMapping("/costomerJoinForm")
    public String costomerJoinForm(Principal principal, Model model) {

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

        return "join/costomerJoinForm.html";
    }



    @GetMapping("/businessOk")
    public String businessOk(Principal principal, Model model) {

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

        return "join/businessOk.html";

    }

    @PostMapping("/businessOk")
    public String businessNumber(@RequestParam("bzNum")String bzNum,
                                 HttpSession session,
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
        
        
        // 사업자 번호를 session에 저장
        session.setAttribute("bzNum", bzNum);
        log.info("businessOk 의 session에 담긴 bzNum : " + session.getAttribute("bzNum"));
        return "join/businessTerms.html";
    }


    @GetMapping("/businessTerms")
    public String businessTerms(HttpSession session, Principal principal, Model model) {

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

        return "join/businessTerms.html";
    }

    @PostMapping("/businessTerms")
    public String businessNumberForm(HttpSession session, Principal principal, Model model) {

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


        session.setAttribute("bzNum", session.getAttribute("bzNum"));
        log.info("businessTerms 의 session에 담긴 값 : " + session.getAttribute("bzNum"));

        return "join/businessJoinForm.html";
    }




    @GetMapping("/businessJoinForm")
    public String businessJoinForm(HttpSession session, Principal principal, Model model) {

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
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck,
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


        ///////


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

            return "redirect:/login";
        }
        return "redirect:index3";
    }




    @PostMapping("/businessJoinForm")
    public String businessJoinOk(@ModelAttribute MemberDTO memberDto,
                                 @ModelAttribute CompanyDTO companyDto,
                                 @RequestParam(name = "email_check", defaultValue = "0")int emailCheck,
                                 HttpSession session,
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


        ///////

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

            return "redirect:/login";
        }
        return "redirect:index3";
    }



    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("member_id")String memberId) {
        return memberService.idCheck(memberId);
    }


    @PostMapping("/nicknameCheck")
    @ResponseBody
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
