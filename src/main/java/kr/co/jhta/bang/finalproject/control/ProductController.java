package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    //상품리스트-------------------------------------------------------------------------------------------------
    @GetMapping("/productList")
    public String productList(Model model, @RequestParam("cno") int cno){

        model.addAttribute("list", service.selectAll(cno));
        List<ProductListDTO> list =  service.selectAll(cno);
//        for(ProductListDTO dto : list ){
//            log.info(dto.getImg());
//        }

        String img ="";
        String banner="";

        if(cno==1) {
            img = "/images/banner_speaker.jpg";
            banner="SPEAKER";
        }
        else if(cno==2) {
            img = "/images/banner_earphone.jpg";
            banner="EARPHONE";
        }

        else if(cno==3) {
            img = "/images/banner_headphone.jpg";
            banner="HEADPHONE";
        }
        else if(cno==4) {
            img = "/images/banner_acc.jpg";
            banner="ACCESSORIES";
        }

        //카테고리 파라미터 값을 넘기면서 배너이미지와 배너텍스트도 함께 전달
        model.addAttribute("img",img);
        model.addAttribute("banner",banner);

        int total = service.getTotal(cno);
        model.addAttribute("total",total);


//        List<ProductDTO> list = service.selectAll();
//        log.info("list : {}",list);
//
//        for(ProductDTO dto: list){
//            log.info("dto : {}", dto.getProduct_number());
//        }
        return "product/productList";
    }

    //상세 페이지 -------------------------------------------------------------------------------------------------
    @GetMapping("/productDetail")
    public String detail(Model model, @RequestParam("pno") int pno){
        model.addAttribute("productDto", service.selectOne(pno));

        return "product/productDetail";
    }

}
