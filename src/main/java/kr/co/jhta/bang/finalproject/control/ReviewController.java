package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.service.ReviewService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService service;
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
    public String reviewDetail(@RequestParam("reply_number")int reply_number, Model model){
        model.addAttribute("dto", service.findByReply_number(reply_number));
        log.info(">>>>>>>>>>>>>>>>>>>>reviewDetail {}", service.findByReply_number(reply_number));
        return "review/reviewDetail";
    }

    @GetMapping("/productDetail")
    public String productDetail(Model model){
        model.addAttribute("list", service.getAll());
//        log.info(">>>>>>>>>>>>>>>>>>>>>reviewList {}", service.findAllReply());
        return "review/productDetail";
    }

    @GetMapping("/reviewWrite")
    public String reviewWrite(){

        return "review/reviewWrite";
    }

    @PostMapping("/reviewWrite")
    public String reviewWriteOk(@ModelAttribute ReviewDTO dto){
        service.writeReply(dto); // dto 객체에는 사용자가 작성한 리뷰 데이터가 담겨있음
        log.info(">>>>>>>>>>>>>>>>>>>>dto {}", dto);
        return "redirect:/review/reviewList"; // 리뷰 리스트 페이지로 리다이렉트
    }

    @GetMapping("/reviewDelete")
    public String reviewDelete(@RequestParam("reply_number")int reply_number){
        service.deleteReview(reply_number);
        return "redirect:/review/reviewList";
    }

    @GetMapping("/reviewModify")
    public String modifyForm(){
        return"review/reviewModify";
    }
}
