package kr.co.jhta.bang.finalproject.Control;


import kr.co.jhta.bang.finalproject.dao.DeptDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class DeptController {

    @Autowired
    DeptDAO dao;

    @GetMapping("/index")
    public String list(Model model) {
        model.addAttribute("list", dao.getAll());
        return "index";
    }

}
