package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;


    @GetMapping("/productList")
    public String productList(Model model){
        //@requestParam 으로 카테고리 값을 받아서 ...

        model.addAttribute("list", service.selectAll());

//        List<ProductDTO> list = service.selectAll();
//        log.info("list : {}",list);
//
//        for(ProductDTO dto: list){
//            log.info("dto : {}", dto.getProduct_number());
//        }
        return "product/productList";
    }


}
