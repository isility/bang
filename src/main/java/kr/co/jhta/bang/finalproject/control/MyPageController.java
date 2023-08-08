package kr.co.jhta.bang.finalproject.control;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyPageController {

    @RequestMapping("/mypage")
    public String mypage(HttpServletRequest req, @RequestParam(name = "my", required = false, defaultValue = "1")int my) {
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


    @PostMapping("/mypage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserInfo(Principal principal,
                                                           HttpServletRequest request,
                                                           Model model) {
        String username = principal.getName();
        model.addAttribute("username", username);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", username);

        // 사용자가 인증된 상태인지 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication.isAuthenticated();

        // 인증 상태를 타임리프 컨텍스트에 추가
        RequestContextUtils.getOutputFlashMap(request).put("isAuthenticated", isAuthenticated);

        return ResponseEntity.ok(userInfo);
    }
}
