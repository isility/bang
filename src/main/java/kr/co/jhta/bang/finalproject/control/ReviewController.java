package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.file.FileValidator;
import kr.co.jhta.bang.finalproject.file.UploadFile;
import kr.co.jhta.bang.finalproject.service.ReviewService;
import kr.co.jhta.bang.finalproject.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    ReviewService service;

    @Autowired
    FileValidator fileValidator;



    @GetMapping("/reviewList")
    public String reviewList(
            Model model,
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
        return "review/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(@RequestParam("replyNumber") int replyNumber, Model model, Model model2){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        log.info("-----------------replyNumber : {}", replyNumber);
        //log.info(">>>>>>>>>>>>>>>>>>>>GET {}", service.commentsSave(replyNumber));
        model2.addAttribute("list", service.findAllByReplyRef(replyNumber));

        return "review/reviewDetail";
    }

    @PostMapping("/reviewDetail")
    public @ResponseBody List<ReviewDTO> save(@ModelAttribute ReviewDTO reviewDTO){


        log.info("reviewDTO :   {} ", reviewDTO);
        //service.firstCommentsSave(reviewDTO);
        List<ReviewDTO> savedReviewList = service.firstCommentsSave(reviewDTO);



        return savedReviewList;
    }

    @GetMapping("/reviewWrite")
    public String reviewWrite(){
        return "review/reviewWrite";
    }

    @PostMapping("/reviewWrite")
    public ModelAndView reviewWriteOk(HttpServletRequest req, @ModelAttribute UploadFile file, BindingResult result,
                                      @ModelAttribute ReviewDTO reviewDTO, @RequestParam("star") int star, Model model){
        reviewDTO.setReplyScore(star);
        service.writeReply(reviewDTO); // reviewDTO 객체에는 사용자가 작성한 리뷰 데이터가 담겨있음
        log.info(">>>>>>>>>>>>>>>>>>>>reviewDTO {}", reviewDTO);



        fileValidator.validate(file,result);
        if(result.hasErrors()){
            return new ModelAndView("review/reviewWrite");
        }

        HttpSession session  = req.getSession();
        ServletContext application = session.getServletContext();

        String filePath = application.getRealPath("/data");

        System.out.println("file : " + file);
        System.out.println("file.getFile() : " + file.getFile());

        MultipartFile mfile = file.getFile();

        String fileName = mfile.getOriginalFilename();

        File f = new File(filePath + "/" + fileName);
        try {
            mfile.transferTo(f);
        } catch(IllegalStateException e) {
            e.printStackTrace();
        } catch(IOException e) {
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("fileName",f.getName());
        mav.addObject("filePath", "../data/" + f.getName());
        mav.setViewName("redirect:review/reviewList");

        return mav; // 리뷰 리스트 페이지로 리다이렉트
    }

    @GetMapping("/reviewDelete")
    public String reviewDelete(@RequestParam("replyNumber")int replyNumber){
        service.deleteReview(replyNumber);
        return "redirect:/review/reviewList";
    }

    @GetMapping("/reviewModify")
    public String modifyForm(@RequestParam("replyNumber")int replyNumber, Model model){
        model.addAttribute("reviewDTO", service.findByReply_number(replyNumber));
        return"review/reviewModify";
    }

    @PostMapping("/reviewModify")
    public String modifyReply(@ModelAttribute ReviewDTO reviewDTO, @RequestParam("star") int star){
        reviewDTO.setReplyScore(star);
        service.modifyReview(reviewDTO);
        log.info(">>>>>>>>>>>>>dto {} ", reviewDTO);
        return "redirect:/review/reviewList";
    }






}
