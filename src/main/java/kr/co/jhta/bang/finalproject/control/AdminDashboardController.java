package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.PaymentDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    PaymentDetailService service;

    @GetMapping("/dashboard")
    public String dashboard(Model model){


        model.addAttribute("PaymentDetailDTO", service.getFourTable());
        log.info(">>>>>>>>>>> {} ", service.getFourTable());
        return "/admin/dashboard";
    }

}