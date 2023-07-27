package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/qna")
public class QnaController {
    @Autowired
    QnaService service;

    @GetMapping("/qlist")
    public String list(Model model){
        model.addAttribute("list", service.selectAll());
        return "qna/qnaList";

    }

    @GetMapping("/qdetail")
    public String form(@RequestParam("qna_number")int qnaNumber, Model model){
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qna/qnaDetail";
    }

    @GetMapping("/qwrite")
    public String writeForm(){
        return "qna/qnaWrite";
    }

    @PostMapping("/qwrite")
    public String writeOk(@ModelAttribute QnaDTO dto, HttpServletRequest req){
        service.addOne(dto);
        return "redirect:/qna/qlist";
    }

    @GetMapping("/qmodify")
    public String modifyForm(@RequestParam("qna_number")int qnaNumber, Model model) {
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qna/qnaModify";
    }

    @PostMapping("/qmodify")
    public String modifyOk(@ModelAttribute QnaDTO dto) {
        service.modifyOne(dto);
        return "redirect:/qna/qlist";
    }

    @GetMapping("/qdelete")
    public String deleteOk(@RequestParam("qna_number")int qnaNumber) {
        service.removeOne(qnaNumber);
        return "redirect:/qna/qlist";
    }
}
