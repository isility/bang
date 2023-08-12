package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    IndexService service;

    @GetMapping ("/")
    public String home(Model model, Principal principal){
        log.info(">>>>>>>>> home ");

        model.addAttribute("speakerList", service.selectAllSpeaker());
        log.info(""+service.selectAllSpeaker());

        model.addAttribute("earphonelist", service.selectAllEarphone());
        List<ProductListDTO> earphonelist =  service.selectAllEarphone();

        model.addAttribute("headphonelist", service.selectAllHeadphone());
        List<ProductListDTO> headphonelist =  service.selectAllHeadphone();

        if (principal != null) {
            if (principal.getName() != null) {
                model.addAttribute("username", principal.getName());
            }
        } else {
            model.addAttribute("username", "Guest"); // 로그인하지 않은 사용자는 "Guest"라는 이름으로 보내기
        }

        return "/index3";
    }
}