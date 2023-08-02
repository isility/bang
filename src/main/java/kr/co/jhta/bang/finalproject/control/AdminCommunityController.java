package kr.co.jhta.bang.finalproject.control;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminCommunityController {

    @GetMapping("/communityList")
    public String getCommunity(){
        log.info(">>>>>>>>move to community<<<<<<<< ");
        return "/admin/communityList";
    }
}
