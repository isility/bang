package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class NoticeController {

    @Autowired
    NoticeService service;

    @GetMapping("notice/noticeList")
    public String list(Model model){
        model.addAttribute("list", service.selectAll());
        return "notice/noticeList";
    }

    @GetMapping("notice/noticeDetail")
    public String form(@RequestParam("notice_number")int noticeNumber, Model model){
        model.addAttribute("dto", service.selectOne(noticeNumber));
        return "notice/noticeDetail";
    }

    @GetMapping("notice/noticeWrite")
    public String writeForm(){
        return "notice/noticeWrite";
    }

    @PostMapping("notice/noticeWrite")
    public String writeOk(@ModelAttribute NoticeDTO dto, HttpServletRequest req){
        service.addOne(dto);
        return "redirect:/notice/noticeList";
    }

    @GetMapping("notice/noticeModify")
    public String modifyForm(@RequestParam("notice_number")int noticeNumber, Model model) {
        model.addAttribute("dto", service.selectOne(noticeNumber));
        return "notice/noticeModify";
    }

    @PostMapping("notice/noticeModify")
    public String modifyOk(@ModelAttribute NoticeDTO dto) {
        service.modifyOne(dto);
        return "redirect:/notice/noticeList";
    }

    @GetMapping("notice/noticeDelete")
    public String deleteOk(@RequestParam("notice_number")int noticeNumber) {
        service.removeOne(noticeNumber);
        return "redirect:/notice/noticeList";
    }

}
