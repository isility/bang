package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.NoticeService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/")
public class NoticeController {

    @Autowired
    NoticeService service;

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
        model.addAttribute("username", principal.getName());

        return "notice/noticeList";
    }

    @GetMapping("notice/noticeDetail")
    public String form(@RequestParam("noticeNumber")int noticeNumber, Model model, Principal principal){
        model.addAttribute("dto", service.selectOne(noticeNumber));
        model.addAttribute("username", principal.getName());

        return "notice/noticeDetail";
    }

    @GetMapping("notice/noticeWrite")
    public String writeForm(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "notice/noticeWrite";
    }

    @PostMapping("notice/noticeWrite")
    public String writeOk(@ModelAttribute NoticeDTO dto, HttpServletRequest req){
        service.addOne(dto);
        return "redirect:/notice/noticeList";
    }

    @GetMapping("notice/noticeModify")
    public String modifyForm(@RequestParam("noticeNumber")int noticeNumber, Model model, Principal principal) {
        model.addAttribute("dto", service.selectOne(noticeNumber));
        model.addAttribute("username", principal.getName());
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
