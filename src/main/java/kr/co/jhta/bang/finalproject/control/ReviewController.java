package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.file.FileValidator;
import kr.co.jhta.bang.finalproject.file.UploadFile;
import kr.co.jhta.bang.finalproject.service.ReviewService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService service;

    @Autowired
    FileValidator fileValidator;



    @GetMapping("/reviewList")
    public String reviewList(
            Model model,
            @RequestParam(name="currentPage", defaultValue = "1")int currentPage
    ){
        // 총 게시물 수
        int totalNumber = service.getTotal();
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

        return "review/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("replyNumber") int replyNumber, Model model, Model model2){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        log.info("-----------------replyNumber : {}", replyNumber);
        //log.info(">>>>>>>>>>>>>>>>>>>>GET {}", service.commentsSave(replyNumber));
        model2.addAttribute("list", service.findAllByReplyRef(replyNumber));

        return "review/reviewDetail";
    }

    @PostMapping("/reviewDetail")
    public @ResponseBody List<ReviewDTO> save(@ModelAttribute ReviewDTO reviewDTO){


        log.info("reviewDTO :   {} ", reviewDTO);
        //service.firstCommentsSave(reviewDTO);
        List<ReviewDTO> savedReviewList = service.firstCommentsSave(reviewDTO);



        return savedReviewList;
    }

    @GetMapping("/reviewWrite")
    public String reviewWrite(Model model, Principal principal) {
        if (principal == null || principal.getName() == null || principal.getName().isEmpty()) {
            return "redirect:/review/reviewList";
        }

        List<ReviewDTO> list = service.getOneByMemberId(principal.getName());
        model.addAttribute("dto", list);

        return "review/reviewWrite";
    }

    @PostMapping("/reviewWrite")
    public String reviewWriteOk(Principal principal,
                                @ModelAttribute ReviewDTO reviewDTO, @RequestParam("star") int star,@RequestParam("productName")String productName, Model model) {


            reviewDTO.setReplyScore(star);
            reviewDTO.setMember_id(principal.getName());
            reviewDTO.setReplyWriter(principal.getName());
            reviewDTO.setProductNumber(service.getProductNumberByName(productName));
            reviewDTO.setProductName(productName);
            service.writeReply(reviewDTO);
            return "redirect:/review/reviewList";
    }



    @GetMapping("/reviewDelete")
    @ResponseBody
    public String reviewDelete(@RequestParam("replyNumber")int replyNumber, @RequestParam("member_id")String member_id,
                               Principal principal){
        log.info(""+ member_id);
        log.info(""+ replyNumber);
        log.info(principal.getName());
        if(principal.getName().equals(member_id)){
            service.deleteReview(replyNumber);
            return "삭제되었습니다.";
        }else {
            return "권한이 없습니다.";
        }
    }

    @PostMapping("/reviewModify")
    @ResponseBody
    public String modifyForm(@RequestParam("replyNumber")int replyNumber, @RequestParam("member_id")String member_id, Model model, Principal principal){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));

        if(principal.getName().equals(member_id)){
            return "성공";
        }else
            return "fail";
    }

    @GetMapping("/reviewModify")
    public String modifyReply(@RequestParam("replyNumber")int replyNumber, Model model){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        return "review/reviewModify";
    }

    @PostMapping("/reviewModifyOk")
    public String modifyOk(@ModelAttribute ReviewDTO reviewDTO, @RequestParam("star")int star){
        reviewDTO.setReplyScore(star);
        service.modifyReview(reviewDTO);
       return "redirect:/review/reviewList";
    }






}
