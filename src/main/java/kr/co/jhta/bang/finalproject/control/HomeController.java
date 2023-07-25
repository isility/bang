package kr.co.jhta.bang.finalproject.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/index")
    public String home(){
        log.info(">>>>>>>>> home ");
        return "index";
    }
}
