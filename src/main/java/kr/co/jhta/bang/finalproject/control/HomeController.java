package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.IndexService;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    IndexService service;

    @GetMapping ("/")
    public String home(Model model){
        log.info(">>>>>>>>> home ");

        model.addAttribute("speakerList", service.selectAllSpeaker());
        log.info(""+service.selectAllSpeaker());

        model.addAttribute("earphonelist", service.selectAllEarphone());
        List<ProductListDTO> earphonelist =  service.selectAllEarphone();

        model.addAttribute("headphonelist", service.selectAllHeadphone());
        List<ProductListDTO> headphonelist =  service.selectAllHeadphone();

        return "/index3";
    }

    @PostMapping("/index")
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


//    =========================================================================


}