package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.QnaService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class QnaController {
    @Autowired
    QnaService service;

    @GetMapping("qna/qnaList")
    public String list(Model model, @RequestParam(name = "currentPage", defaultValue = "1")int currentPage){
        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int countPerPage = 10;

        Map<String,Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int)map.get("startNo");
        int endNo = (int)map.get("endNo");
        model.addAttribute("list", service.selectAll(startNo, endNo));
        model.addAttribute("map", map);

        return "qna/qnaList";

    }

    @GetMapping("qna/qnaDetail")
    public String form(@RequestParam("qna_number")int qnaNumber, Model model){
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qna/qnaDetail";
    }

    @GetMapping("qna/qnaWrite")
    public String writeForm(){
        return "qna/qnaWrite";
    }

    @PostMapping("qna/qnaWrite")
    public String writeOk(@ModelAttribute QnaDTO dto, HttpServletRequest req){
        service.addOne(dto);
        return "redirect:/qna/qnaList";
    }

    @GetMapping("qna/qnaModify")
    public String modifyForm(@RequestParam("qna_number")int qnaNumber, Model model) {
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qna/qnaModify";
    }

    @PostMapping("qna/qnaModify")
    public String modifyOk(@ModelAttribute QnaDTO dto) {
        service.modifyOne(dto);
        return "redirect:/qna/qnaList";
    }

    @GetMapping("qna/qnaDelete")
    public String deleteOk(@RequestParam("qna_number")int qnaNumber) {
        service.removeOne(qnaNumber);
        return "redirect:/qna/qnaList";
    }
}
