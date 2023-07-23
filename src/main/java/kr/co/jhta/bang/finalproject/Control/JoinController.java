package kr.co.jhta.bang.finalproject.Control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {

    @GetMapping("/join")
    public String join() {
        return "join/join.html";
    }

    @GetMapping("/costomerterms")
    public String terms() {
        return "join/costomerterms.html";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "join/joinForm.html";
    }
}
