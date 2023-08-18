package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.*;
import kr.co.jhta.bang.finalproject.service.AdminService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
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

        return "admin/dashboard";
    }



    @GetMapping("/paymentList")
    public String getPaymentList(Model model,
                                 @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        // 총 게시물 수
        int totalNumber = service.getTotalPaymentDetail();
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
        return "admin/paymentList";
    }
    @PostMapping("/paymentList")
    public String updatePaymentList(
                                    @RequestParam("paymentNumber")int paymentNumber,
                                    @RequestParam("paymentDetailStatus")String paymentDetailStatus){
        PaymentDetailDTO dto = new PaymentDetailDTO();
        dto.setPaymentDetailStatus(paymentDetailStatus);
        dto.setPaymentNumber(paymentNumber);
        service.updatePaymentList(dto);
        log.info(">>>>>> paymentDetailDTO : {}", dto);
        return "redirect:/admin/paymentList";
    }





    @GetMapping("/memberList")
    public String getMemberList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {
        // 총 게시물 수
        int totalNumber = service.getTotalMember();
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
    @PostMapping("/memberModify")
    public String memberModifyOk(@ModelAttribute MemberDTO dto){
        service.updateMemberDetail(dto);
        return "redirect:/admin/memberList";
    }

    @GetMapping("/memberDelete")
    public String memberDelete(@RequestParam("member_id")String member_id){
        service.memberDelete(member_id);
        return "redirect:/admin/memberList" ;
    }




    @GetMapping("/serviceList")
    public String getServiceList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotalService();
        // 페이지당 게시물 수
        int countPerPage = 10;

        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + countPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int) map.get("startNo");
        int endNo = (int) map.get("endNo");

        List<ServiceDTO> serviceList = service.getServicePaging(startNo, endNo);
        log.info(">>>>>>>>>>>>>>>>페이징 {}", serviceList);
        model.addAttribute("list", serviceList);
        model.addAttribute("map", map);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}",serviceList);
        return "admin/serviceList";
    }
    @PostMapping("/serviceList")
    public String updateServiceList( @RequestParam("serviceNumber")int serviceNumber, @RequestParam("serviceType")String serviceType){
        ServiceDTO dto = new ServiceDTO();
        dto.setServiceNumber(serviceNumber);
        dto.setServiceType(serviceType);
        service.updateServiceList(dto);
        log.info(">>>>>> serviceDTO : {}", dto);
        return "redirect:/admin/serviceList";
    }
    @GetMapping("/serviceDelete")
    public String serviceDelete(@RequestParam("serviceNumber")int serviceNumber){
        service.serviceDelete(serviceNumber);
        return "redirect:/admin/serviceList" ;
    }


    @GetMapping("/communityList")
    public String noticeList(Model model, @RequestParam(name = "currentPage", defaultValue = "1")int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotalNotice();
        // 페이지당 게시물 수
        int countPerPage = 10;

        Map<String,Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("endNo");
        model.addAttribute("list", service.selectAllNotice(startNo, endNo));
        model.addAttribute("map", map);

        return "admin/communityList";
    }

    @GetMapping("/noticeWrite")
    public String writeNoticeForm(){
        return "admin/noticeWrite";
    }

    @PostMapping("/noticeWrite")
    public String writeNoticeOk(@ModelAttribute NoticeDTO dto, HttpServletRequest req){
        service.addOneNotice(dto);
        return "redirect:/admin/communityList";
    }

    @GetMapping("/noticeModify")
    public String modifyNoticeForm(@RequestParam("noticeNumber")int noticeNumber, Model model) {
        model.addAttribute("dto", service.selectOneNotice(noticeNumber));
        return "admin/noticeModify";
    }

    @PostMapping("/noticeModify")
    public String modifyNoticeOk(@ModelAttribute NoticeDTO dto) {
        service.modifyOneNotice(dto);
        return "redirect:/admin/communityList";
    }

    @GetMapping("/noticeDelete")
    public String deleteNoticeOk(@RequestParam("noticeNumber")int noticeNumber) {
        service.removeOneNotice(noticeNumber);
        return "redirect:/admin/communityList";
    }

    @GetMapping("/reviewList")
    public String reviewList(
            Model model,
            @RequestParam(name="currentPage", defaultValue = "1")int currentPage
    ){
        // 총 게시물 수
        int totalNumber = service.getTotalReview();
        // 페이지당 게시물 수
        int countPerPage = 10;

        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + countPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        Map<String,Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("endNo");
        model.addAttribute("list", service.findAllReply(startNo, endNo));
        model.addAttribute("map", map);
        return "admin/reviewList";
    }

    @GetMapping("/reviewModify")
    public String modifyForm(@RequestParam("replyNumber")int replyNumber, Model model){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        return "admin/reviewModify";
    }

    @PostMapping("/reviewModify")
    public String modifyReply(@ModelAttribute ReviewDTO reviewDTO, @RequestParam("star") int star){
        reviewDTO.setReplyScore(star);
        service.modifyReview(reviewDTO);
        log.info(">>>>>>>>>>>>>dto {} ", reviewDTO);
        return "redirect:/admin/reviewList";
    }

    @GetMapping("/reviewDelete")
    public String reviewDelete(@RequestParam("replyNumber")int replyNumber){
        service.deleteReview(replyNumber);
        return "redirect:/admin/reviewList";
    }

    @GetMapping("/qnaList")
    public String qnaList(Model model, @RequestParam(name = "currentPage", defaultValue = "1")int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotalQna();
        // 페이지당 게시물 수
        int countPerPage = 10;

        Map<String,Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("endNo");
        model.addAttribute("list", service.selectAllQna(startNo, endNo));
        model.addAttribute("map", map);

        return "admin/qnaList";
    }

    @GetMapping("/qnaWrite")
    public String writeQnaForm(){
        return "admin/qnaWrite";
    }

    @PostMapping("/qnaWrite")
    public String writeQnaOk(@ModelAttribute QnaDTO dto, HttpServletRequest req){
        service.addOneQna(dto);
        return "redirect:/admin/qnaList";
    }

    @GetMapping("/qnaModify")
    public String modifyQnaForm(@RequestParam("qnaNumber")int qnaNumber, Model model) {
        model.addAttribute("dto", service.selectOneQna(qnaNumber));
        return "admin/qnaModify";
    }

    @PostMapping("/qnaModify")
    public String modifyQnaOk(@ModelAttribute QnaDTO dto) {
        service.modifyOneQna(dto);
        return "redirect:/admin/qnaList";
    }

    @GetMapping("/qnaDelete")
    public String deleteQnaOk(@RequestParam("qnaNumber")int qnaNumber) {
        service.removeOneQna(qnaNumber);
        return "redirect:/admin/qnaList";
    }







    @GetMapping("/productList")
    public String getProductList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotalProduct();
        // 페이지당 게시물 수
        int countPerPage = 10;

        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + countPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int) map.get("startNo");
        int endNo = (int) map.get("endNo");

        List<ProductDTO> productList = service.getProductPaging(startNo, endNo);
        log.info(">>>>>>>>>>>>>>>>페이징 {}", productList);
        model.addAttribute("list", productList);
        model.addAttribute("map", map);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}",productList);
        return "admin/productList";
    }
    @PostMapping("/productList")
    public String updateProductList( @RequestParam("productNumber")int productNumber, @RequestParam("productDetail")String productDetail){
        ProductDTO dto = new ProductDTO();
        dto.setProductNumber(productNumber);
        dto.setProductDetail(productDetail);
        service.updateProductList(dto);
        log.info(">>>>>> serviceDTO : {}", dto);
        return "redirect:/admin/productList";
    }

    @GetMapping("/productDelete")
    public String productDelete(@RequestParam("productNumber")int productNumber){
        service.productDelete(productNumber);
        return "redirect:/admin/productList" ;
    }

    @GetMapping("/productModify")
    public String productModify(Model model, @RequestParam("productNumber")int productNumber){
        model.addAttribute("productDTO", service.productDetail(productNumber));
        return "admin/productModify";
    }

    @PostMapping("/productModify")
    public String productModifyOk(@ModelAttribute ProductDTO dto){
        service.updateProductModify(dto);
        return "redirect:/admin/productList";
    }



}