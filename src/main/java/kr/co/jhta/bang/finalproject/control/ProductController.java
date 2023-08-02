package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    PaymentService payService;

    //상품리스트-------------------------------------------------------------------------------------------------
    @GetMapping("/productList")
    public String productList(Model model, @RequestParam("categoryNumber") int categoryNumber){

        model.addAttribute("list", service.selectAll(categoryNumber));
        List<ProductListDTO> list =  service.selectAll(categoryNumber);
//        for(ProductListDTO dto : list ){
//            log.info(dto.getImg());
//        }

        String img ="";
        String banner="";

        if(categoryNumber==1) {
            img = "/images/banner_speaker.jpg";
            banner="SPEAKER";
        }
        else if(categoryNumber==2) {
            img = "/images/banner_earphone.jpg";
            banner="EARPHONE";
        }

        else if(categoryNumber==3) {
            img = "/images/banner_headphone.jpg";
            banner="HEADPHONE";
        }
        else if(categoryNumber==4) {
            img = "/images/banner_acc.jpg";
            banner="ACCESSORIES";
        }

        //카테고리 파라미터 값을 넘기면서 배너이미지와 배너텍스트도 함께 전달
        model.addAttribute("img",img);
        model.addAttribute("banner",banner);

        int total = service.getTotal(categoryNumber);
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
    public String detail(Model model, @RequestParam("productNumber") int productNumber){
        model.addAttribute("productDto", service.selectOne(productNumber));
        model.addAttribute("detailImg", service.selectList(productNumber));

        return "product/productDetail";
    }

    //장바구니---------------------------------------------------------------------------------------------
    @GetMapping("/cart")
    public String cart(Model model, Principal principal){
        model.addAttribute("cartList", payService.cartlist(principal.getName()));
        return "product/cart";
    }
}
