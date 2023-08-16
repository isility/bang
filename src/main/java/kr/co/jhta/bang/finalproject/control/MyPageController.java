package kr.co.jhta.bang.finalproject.control;


import kr.co.jhta.bang.finalproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MyPageController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping("/mypage")
    public String mypage(@RequestParam(name = "my", required = false, defaultValue = "1")int my, Principal principal, Model model) {
        if(principal != null) {
            if (my == 1) {
                return "mypage/myPage";
            } else if (my == 2) {
                return "mypage/myWish";
            } else if (my == 3) {
                model.addAttribute("order",paymentService.orderList(principal.getName()));
                return "mypage/myService";
            } else if (my == 4) {
                return "mypage/myCoupon";
            } else if (my == 5) {
                return "mypage/myQna";
            } else if (my == 6) {
                return "mypage/myModify";
            } else if (my == 7) {
                return "mypage/mySignOut";
            } else
                return "mypage/myPage";
        }else
            return "redirect:/mypage/myPage";
    }
}
