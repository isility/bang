package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.ReviewService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService service;
    @GetMapping(value="/reviewList")
    public String reviewList(
            Model model,
            @RequestParam(name="currentPage", defaultValue = "1")int currentPage
    ){
        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int recordPerPage = 10;

        Map<String,Object> map = PageUtil.getPageData(totalNumber, recordPerPage, currentPage);
        log.info("총 게시물 수 >>>>>>>" + totalNumber);
        log.info("페이지당 게시물 수 >>>>>>>" + recordPerPage);
        log.info("현재 페이지 번호 >>>>>>>" + currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("startNo");
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
}
