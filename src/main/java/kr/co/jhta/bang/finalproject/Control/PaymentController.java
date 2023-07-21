package kr.co.jhta.bang.finalproject.Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/payment")
public class PaymentController {
    @GetMapping("/main")
    public String list(Model model) {

        return "payment/main";
    }
}
