package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.MemberUserTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberUserTokenService tokenService;

    @GetMapping("/login")
    public String login() {
        return "login/login.html";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "login/login.html";

    }

 /*   @PostMapping("/login")
    public ResponseEntity<String> loginToken(@RequestParam("username")String username,
                                             @RequestParam("password")String password) {

        return ResponseEntity.ok(tokenService.loginToken(username, password));
    }
*/
}
