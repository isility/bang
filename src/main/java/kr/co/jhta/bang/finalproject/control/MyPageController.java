package kr.co.jhta.bang.finalproject.control;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyPageController {

    @RequestMapping("/mypage")
    public String mypage(HttpServletRequest req, @RequestParam(name = "my", required = false, defaultValue = "1")int my ) {
//        System.out.println("---------------------------------------------------------" + req);
       // System.out.println(my);
        if (req != null) {
            if (my == 1) {
                return "mypage/myPage";
            } else if (my == 2) {
                return "mypage/myWish";
            } else if (my == 3) {
                return "mypage/myService";
            } else if (my == 4) {
                return "mypage/myCoupon";
            } else if (my == 5) {
                return "mypage/myQna";
            } else if (my == 6) {
                return "mypage/myModify";
            } else if (my == 7) {
                return "mypage/mySignOut";
            } else
                return "mypage/myPage";
        }else
            return "redirect:/mypage/myPage";
//        return null;
    }
}
