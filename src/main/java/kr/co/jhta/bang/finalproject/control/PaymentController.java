package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.ProductDTO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.KakaoPayService;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Setter(onMethod_ = @Autowired)
    private KakaoPayService kakaopay;

    @Setter
    @Autowired
    PaymentService payService;
    @Autowired
    MemberDAO memberDAO;


    @RequestMapping("")
    public String kakaoPayGet(HttpSession session, Principal principal) {

        int tp = 0;

        List<CartDTO> list =  payService.cartlist("cpy222");
        for(CartDTO dto : list) {
            tp += dto.getProductPrice() * dto.getCartQuantity();
        }

        session.setAttribute("totalPrice",tp);
        session.setAttribute("cartList",payService.cartlist("cpy222"));
        session.setAttribute("member", memberDAO.selectOne("cpy222"));

        log.info("카카페 래디1");
        return "payment/kakaoPay";
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay() {
        log.info("카카페 래디2");
        return "redirect:" + kakaopay.kakaoPayReady();
    }

    @GetMapping("/kakaoPaySuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("카카페 성공");
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
    }

    @RequestMapping("/kakaoPayCancel")
    public String kakaoPayCancel() {
        return "payment/kakaoPayCancel";
    }
    @RequestMapping("/kakaoPaySuccessFail")
    public String kakaoPaySuccessFail() {
        return "payment/kakaoPaySuccessFail";
    }

}
