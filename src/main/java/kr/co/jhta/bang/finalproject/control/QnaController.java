package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/community")
public class QnaController {
    @Autowired
    QnaService service;

    @GetMapping("/qnaList")
    public String list(Model model){
        model.addAttribute("list", service.selectAll());
        return "qnaList";

    }

    @GetMapping("/qnaDetail")
    public String form(@RequestParam("qna_number")int qnaNumber, Model model){
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qnaDetail";
    }

}
