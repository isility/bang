package kr.co.jhta.bang.finalproject.control;


import kr.co.jhta.bang.finalproject.dto.PaymentDTO;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class MyPageController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProductService productService;



    @RequestMapping("/mypage")
    public String mypage(@RequestParam(name = "my", required = false, defaultValue = "1")int my, Principal principal, Model model) {
        if(principal != null) {
            if (my == 1) {
                model.addAttribute("orderlist",paymentService.orderList(principal.getName()));
                return "mypage/myPage";
            } else if (my == 2) {
                return "mypage/myWish";
            } else if (my == 3) {
                return "mypage/myService";
            } else if (my == 4) {
                return "mypage/myCoupon";
            } else if (my == 5) {
                return "mypage/myQna";
            } else if (my == 6) {
                return "mypage/myModify";
            } else if (my == 7) {
                return "mypage/mySignOut";
            } else {
                model.addAttribute("orderlist", paymentService.orderList(principal.getName()));
                return "mypage/myPage";
            }
        }else
            return "redirect:/";
    }

    @RequestMapping("/orderdetail")
    public String orderdetail(@RequestParam("pno")int pno, Principal principal, Model model){
        PaymentDTO dto = new PaymentDTO();
        dto.setMemberID(principal.getName());
        dto.setPaymentNumber(pno);
        log.info("dto : " + dto);
        log.info("널값 찾어"+paymentService.orderProductList(dto));
        model.addAttribute("orderlist",paymentService.orderProductList(dto));

        return "mypage/orderDetail";
    }
}
