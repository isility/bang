package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.NoticeService;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
@Slf4j
@Controller
@RequestMapping("/")
public class NoticeController {

    @Autowired
    NoticeService service;

    @Autowired
    PaymentService payService;

    @GetMapping("notice/noticeList")
    public String list(Model model, Principal principal, @RequestParam(name = "currentPage", defaultValue = "1")int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int countPerPage = 10;

        Map<String,Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("endNo");
        model.addAttribute("list", service.selectAll(startNo, endNo));
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

        return "notice/noticeList";
    }

    @GetMapping("notice/noticeDetail")
    public String form(@RequestParam("noticeNumber")int noticeNumber, Model model, Principal principal){
        model.addAttribute("dto", service.selectOne(noticeNumber));


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

        return "notice/noticeDetail";
    }

    @GetMapping("admin/notice/noticeWrite")
    public String writeForm(Model model, Principal principal){


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

        return "notice/noticeWrite";
    }

    @PostMapping("admin/notice/noticeWrite")
    public String writeOk(@ModelAttribute NoticeDTO dto, HttpServletRequest req){

        service.addOne(dto);

        return "redirect:/notice/noticeList";
    }

    @GetMapping("notice/noticeModify")
    public String modifyForm(@RequestParam("noticeNumber")int noticeNumber, Model model, Principal principal) {
        model.addAttribute("dto", service.selectOne(noticeNumber));


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

        return "notice/noticeModify";
    }

    @PostMapping("notice/noticeModify")
    public String modifyOk(@ModelAttribute NoticeDTO dto) {
        service.modifyOne(dto);
        return "redirect:/notice/noticeList";
    }

    @GetMapping("notice/noticeDelete")
    public String deleteOk(@RequestParam("noticeNumber")int noticeNumber) {
        service.removeOne(noticeNumber);
        return "redirect:/notice/noticeList";
    }

}
