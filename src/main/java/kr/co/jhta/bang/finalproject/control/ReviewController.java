package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService service;
    @GetMapping(value="/reviewList")
    public String reviewList(
            Model model
    ){


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
