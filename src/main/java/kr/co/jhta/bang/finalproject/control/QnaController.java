package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.service.QnaService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class QnaController {
    @Autowired
    QnaService service;

    @GetMapping("qna/getQnaTypeValues")
    public ResponseEntity<List<QnaDTO>> getQnaTypeValues(@RequestParam String value) {
        List<QnaDTO> qnaList = service.getQnaListByType(value); // 선택한 타입에 해당하는 Q&A 리스트 조회
        return new ResponseEntity<>(qnaList, HttpStatus.OK);
    }


    /*@GetMapping("qna/qnaList")
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

    }*/

    @GetMapping("qna/qnaList")
    public String list(Model model,
                       @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                       @RequestParam(name = "qnaType", defaultValue = "카테고리") String qnaType,
                       Principal principal) {
        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 페이지당 게시물 수
        int countPerPage = 10;

        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage);
        int startNo = (int) map.get("startNo");
        int endNo = (int) map.get("endNo");

        List<QnaDTO> qnaList;

        if ("카테고리".equals(qnaType)) {
            qnaList = service.selectAll(startNo, endNo);
        } else {
            qnaList = service.getQnaListByType(qnaType);
        }

        model.addAttribute("list", qnaList);
        model.addAttribute("map", map);
        model.addAttribute("qnaType", qnaType); // qnaType을 모델에 추가하여 View에서 사용할 수 있도록 함
        model.addAttribute("username", principal.getName());
        return "qna/qnaList";
    }





    @GetMapping("qna/qnaDetail")
    public String form(@RequestParam("qnaNumber")int qnaNumber, Model model, Principal principal){
        model.addAttribute("dto", service.selectOne(qnaNumber));
        model.addAttribute("username", principal.getName());
        return "qna/qnaDetail";
    }




    @GetMapping("qna/qnaWrite")
    public String writeForm(Model model, Principal principal){
        model.addAttribute("username", principal.getName());
        return "qna/qnaWrite";
    }

    @PostMapping("qna/qnaWrite")
    public String writeOk(@ModelAttribute QnaDTO dto, HttpServletRequest req){
        service.addOne(dto);
        return "redirect:/qna/qnaList";
    }

    @GetMapping("qna/qnaModify")
    public String modifyForm(@RequestParam("qnaNumber")int qnaNumber, Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("dto", service.selectOne(qnaNumber));
        return "qna/qnaModify";
    }

    @PostMapping("qna/qnaModify")
    public String modifyOk(@ModelAttribute QnaDTO dto) {
        service.modifyOne(dto);
        return "redirect:/qna/qnaList";
    }

    @GetMapping("qna/qnaDelete")
    public String deleteOk(@RequestParam("qnaNumber")int qnaNumber) {
        service.removeOne(qnaNumber);
        return "redirect:/qna/qnaList";
    }

}
