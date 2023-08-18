package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.service.ReviewService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    PaymentService payService;




    @GetMapping("/reviewList")
    public String reviewList(
            Model model,Principal principal,
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
        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }

        return "review/reviewList";

    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("replyNumber") int replyNumber, Model model,Principal principal, Model model2){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        log.info("-----------------replyNumber : {}", replyNumber);
        //log.info(">>>>>>>>>>>>>>>>>>>>GET {}", service.commentsSave(replyNumber));
        model2.addAttribute("list", service.findAllByReplyRef(replyNumber));

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }
        return "review/reviewDetail";
    }

    @PostMapping("/reviewDetail")
    public @ResponseBody List<ReviewDTO> save(@ModelAttribute ReviewDTO reviewDTO, Principal principal){



        log.info("reviewDTO :   {} ", reviewDTO);
        if (principal != null) {
            reviewDTO.setReplyWriter(principal.getName());
        } else {
            reviewDTO.setReplyWriter("Guest");
        }

        //service.firstCommentsSave(reviewDTO);
        List<ReviewDTO> savedReviewList = service.firstCommentsSave(reviewDTO);



        return savedReviewList;
    }

    @GetMapping("/reviewWrite")
    public String reviewWrite(Model model, Principal principal) {

        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());
        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }

        if (principal == null || principal.getName() == null || principal.getName().isEmpty()) {
            return "redirect:/review/reviewList";
        }

        // 로그인한 아이디로 구매한 이력 확인
        if(service.getOneByMemberId(principal.getName()) == null || service.getOneByMemberId(principal.getName()).isEmpty() ) {

            return "redirect:/review/reviewList";
        }
        else {
            List<ReviewDTO> list = service.getOneByMemberId(principal.getName());
            service.getOneByMemberId(principal.getName());
            model.addAttribute("dto", list);
            log.info("getOneByMemberId ////////////////// {}", list);
            return "review/reviewWrite";
        }
    }

    @PostMapping("/reviewWrite")
    public String reviewWriteOk(Principal principal,
                                @ModelAttribute ReviewDTO reviewDTO,
                                @RequestParam("star") int star, Model model) {

        if (principal == null) {
            // 로그인되지 않은 경우 처리
            return "redirect:/review/reviewList";
        }
            reviewDTO.setReplyScore(star);
            reviewDTO.setMember_id(principal.getName());
            reviewDTO.setReplyWriter(principal.getName());
            log.info(reviewDTO.getProductName());
            reviewDTO.setProductNumber(service.getProductNumber(reviewDTO.getProductName()));
            // 상품명을 가져오기 위한 코드 수정

            service.writeReply(reviewDTO);
            return "redirect:/review/reviewList";

    }
@RequestMapping("productnamecheck")
@ResponseBody
public int productnamecheck(@RequestParam("productName")String productName){
        return service.getProductNumber(productName);
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
    public String modifyForm(@RequestParam("replyNumber")int replyNumber, @RequestParam("member_id")String member_id, Model model, Principal principal) {

        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        if (principal.getName().equals(member_id)) {
            return "성공";
        } else if(principal.getName().equals(null) || principal.getName() == null){
            return "null";
        }
        else{
            return "실패";
        }


    }

    @GetMapping("/reviewModify")
    public String modifyReply(@RequestParam("replyNumber")int replyNumber, Model model, Principal principal, @RequestParam("member_id")String member_id){
        log.info(""+ member_id);
        if (principal != null) {
            int cnt = 0;
            log.info("로그인된 사용자");
            log.info("Authentication 의 반환객체 : {}", principal.getName());
            for (CartDTO dto : payService.cartlist(principal.getName()))
                cnt++;
            model.addAttribute("cartListCount", cnt);
            model.addAttribute("username", principal.getName());

        } else {
            log.info("로그인하지 않은 사용자");
            model.addAttribute("username", "Guest 님"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
            model.addAttribute("cartListCount", 0);
        }

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
