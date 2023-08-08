package kr.co.jhta.bang.finalproject.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminChartController {

    @Autowired
    AdminService service;



//    @GetMapping("/dashboard")
//    public String chart(Model model) {
//
//
//        return "/admin/dashboard/chart";
//
//    }





}
