package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    IndexService service;

    @Autowired
    MemberUserDetails memberUserDetails;


    @Autowired
    MemberUserDetailService memberUserDetailService;

    @Autowired
    MemberService memberService;

    @Autowired
    PaymentService paymentService;

    @GetMapping ("/")
    public String home(Model model, Principal principal){
        log.info(">>>>>>>>> home ");


//        int cnt = 0;
//        if (principal.getName() != null) {
//            List<CartDTO> cl = payService.cartlist(principal.getName());
//            for (CartDTO dto : cl)
//                cnt += 1;
//            model.addAttribute("cartListCount", cnt);
//        }
//        else{
//            model.addAttribute("cartListCount", cnt);
//        }



        model.addAttribute("speakerList", service.selectAllSpeaker());
        log.info(""+service.selectAllSpeaker());

        model.addAttribute("earphonelist", service.selectAllEarphone());
        List<ProductListDTO> earphonelist =  service.selectAllEarphone();

        model.addAttribute("headphonelist", service.selectAllHeadphone());
        List<ProductListDTO> headphonelist =  service.selectAllHeadphone();

//        =========================================================================
        if (principal != null) {
            int cnt =0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());

            for(CartDTO dto : paymentService.cartlist(principal.getName()))
                cnt ++;

            model.addAttribute("cartListCount",cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount",0);
        }

        //        =========================================================================
        return "/index3";
    }

}