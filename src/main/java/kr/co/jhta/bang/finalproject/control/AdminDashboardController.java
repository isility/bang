package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.service.PaymentDetailService;
import kr.co.jhta.bang.finalproject.service.QnaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    PaymentDetailService service;
    @GetMapping("/dashboard")
    public String dashboard(Model model){


        model.addAttribute("PaymentDetailDTO", service.getFourTable());
        model.addAttribute("getPriceYear", service.getPriceYear());
       // model.addAttribute("getSalesThisMonth", service.getSalesThisMonth());
        log.info(">>>>>>>>>>>getPriceYear() {} ", service.getPriceYear());
        //log.info(">>>>>>>>>>>getSalesMonth() {} ", service.getSalesThisMonth());
        model.addAttribute("QnaDTO", service.getDashboardQna());
        log.info(">>>>>>>>>getDashboardQna() {}", service.getDashboardQna());
        model.addAttribute("getSalesAvgMonth", service.getSalesAvgMonth());
        //log.info(">>>>>>>>>getSalesAvgMonth() {}", service.getSalesAvgMonth());

        return "/admin/dashboard";
    }









}