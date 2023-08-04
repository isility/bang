package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.PaymentDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminChartController {



    @GetMapping("/dashboard/chart")
    public String chart(Model model){
        return "";
    }
}
