package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.service.AdminService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;
    @GetMapping("/dashboard")
    public String dashboard(Model model){


        model.addAttribute("PaymentDetailDTO", service.getFourTable());
        model.addAttribute("getPriceYear", service.getPriceYear());
        //log.info(">>>>>>>>>>>getPriceYear() {} ", service.getPriceYear());
        //log.info(">>>>>>>>>>>getSalesMonth() {} ", service.getSalesThisMonth());
        model.addAttribute("QnaDTO", service.getDashboardQna());
        //log.info(">>>>>>>>>getDashboardQna() {}", service.getDashboardQna());
        model.addAttribute("getSalesAvgMonth", service.getSalesAvgMonth());
        //log.info(">>>>>>>>>getSalesAvgMonth() {}", service.getSalesAvgMonth());

        return "/admin/dashboard";


    }

    @GetMapping("/paymentList")
    public String getPaymentList(Model model,
                                 @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {


        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int countPerPage = 10;

        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + countPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int) map.get("startNo");
        int endNo = (int) map.get("endNo");

        List<PaymentDetailDTO> paymentDetails = service.getPaging(startNo, endNo);
        log.info(">>>>>>>>>>>>>>>>페이징 {}", paymentDetails);
        model.addAttribute("list", paymentDetails);
        model.addAttribute("map", map);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}",paymentDetails);
        return "/admin/paymentList";
    }

    @PostMapping("/paymentList")
    public String updatePaymentList(
                                    @RequestParam("paymentDetailNumber")int paymentDetailNumber,
                                    @RequestParam("paymentDetailStatus")String paymentDetailStatus){
        PaymentDetailDTO dto = new PaymentDetailDTO();
        dto.setPaymentDetailStatus(paymentDetailStatus);
        dto.setPaymentDetailNumber(paymentDetailNumber);
        service.updatePaymentList(dto);
        log.info(">>>>>> paymentDetailDTO : {}", dto);
        return "redirect:/admin/paymentList";
    }





    @GetMapping("/memberList")
    public String getMemberList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int countPerPage = 10;

        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + countPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int) map.get("startNo");
        int endNo = (int) map.get("endNo");

        List<MemberDTO> memberList = service.getMemberPaging(startNo, endNo);
        log.info(">>>>>>>>>>>>>>>>페이징 {}", memberList);
        model.addAttribute("list", memberList);
        model.addAttribute("map", map);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}",memberList);
        return "admin/memberList";
    }

    @GetMapping("/memberModify")
    public String memberModify(Model model, @RequestParam("member_id")String member_id){

        model.addAttribute("memberDTO", service.memberDetail(member_id));
        return "admin/memberModify" ;
    }
    @PostMapping("/memberDelete")
    public String memberDelete(@RequestParam("member_id")String member_id){

        return "redirect:/admin/memberList" ;
    }

}