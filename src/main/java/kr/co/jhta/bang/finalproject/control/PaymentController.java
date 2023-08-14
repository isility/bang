package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
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
import org.springframework.web.bind.annotation.*;

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
    ProductService productService;
    @Autowired
    MemberDAO memberDAO;

    @GetMapping("/pay")
    public String kakaoPayMain(){

        return "payment/kakaoPay";
    }

    @PostMapping("")
    @ResponseBody
    public void kakaoPayGet(@RequestParam("pnoList[]")int[] pnoList,@RequestParam("quantityList[]")int[] quantityList, HttpSession session, Principal principal) {
        int tp = 0;

        List<CartDTO> list = new ArrayList<>();

        for(int i = 0; i < pnoList.length; i++) {
            CartDTO dto = new CartDTO();
            dto.setProductNumber(pnoList[i]);
            dto.setCartQuantity(quantityList[i]);
            dto.setMemberID(principal.getName());
            list.add(payService.selectList(dto));
            ProductListDTO pdto = productService.selectOne(pnoList[i]);
            tp += pdto.getProductPrice() * quantityList[i];
        }

        log.info("멤버 : " + memberDAO.selectOne(principal.getName()));
        log.info("" + list);
        session.setAttribute("totalPrice",tp);
        session.setAttribute("cartList",list);
        session.setAttribute("member", memberDAO.selectOne(principal.getName()));
        session.setAttribute("memberid", principal.getName());

        log.info("카카페 래디1");
//        return "payment/kakaoPay";
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
