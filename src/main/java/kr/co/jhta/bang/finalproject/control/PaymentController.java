package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.PaymentDTO;
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
    public String kakaoPayMain(Principal principal, Model model){

        if (principal != null) {
            int cnt = 0;
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }

        return "payment/kakaoPay";
    }
    @GetMapping("")
    public String kakaoPayMain(@RequestParam("pno")int pno, @RequestParam("quantity")int quantity, HttpSession session, Principal principal) {
        int tp = 0;

        tp = productService.selectOne(pno).getProductPrice() * quantity;

        session.setAttribute("totalPrice", tp);
        session.setAttribute("cartList", productService.selectOne(pno));
        session.setAttribute("member", memberDAO.selectOne(principal.getName()));
        session.setAttribute("memberid", principal.getName());

        return "payment/pay";
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
    public String kakaoPay(@RequestParam("orderName")String orderName,
                           @RequestParam("orderPhone")String orderPhone,
                           @RequestParam("orderPost")String orderPost,
                           @RequestParam("orderAddr1")String orderAddress1,
                           @RequestParam("orderAddr2")String orderAddress2,HttpSession session) {

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentName(orderName);
        paymentDTO.setPaymentPhone(orderPhone);
        paymentDTO.setPaymentPostal(orderPost);
        paymentDTO.setPaymentAddress1(orderAddress1);
        paymentDTO.setPaymentAddress2(orderAddress2);


        session.setAttribute("order", paymentDTO);

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
