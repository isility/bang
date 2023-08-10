package kr.co.jhta.bang.finalproject.control;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.CartQuantityModifyDTO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import kr.co.jhta.bang.finalproject.service.PaymentService;
import kr.co.jhta.bang.finalproject.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        int cnt = 0;

        model.addAttribute("cartList", payService.cartlist("cpy222"));
        List<CartDTO> list = payService.cartlist("cpy222");
        for (CartDTO dto : list)
            cnt+=1;
        model.addAttribute("cartListCount",cnt);
        model.addAttribute("totalPrice",service.allPrice("cpy222"));

        return "product/cart";
    }

    //장바구니 수량 수정
    @RequestMapping("/updateQuantity")
    @ResponseBody
    public int updateQuantity(@RequestParam("newQuantity")int newQuantity,
                              @RequestParam("pno")int pno,
                              Principal principal
                              ){

        service.cartQuantityUpdateOne(newQuantity, pno, principal.getName());

        return service.selectOne(pno).getProductPrice();
    }

    @PostMapping("/deleteCart")
    @ResponseBody
//    public int cartDeleteProduct(@RequestParam("pnoList")Integer[] pnoList, Principal principal){
    public int cartDeleteProduct(@RequestParam(value = "pnoList[]")int[] pnoList, Principal principal){
        int cnt = 0;
        log.info(principal.getName());

        for(int pno : pnoList){
            CartQuantityModifyDTO dto = new CartQuantityModifyDTO();
            dto.setProductNumber(pno);
            dto.setMemberID(principal.getName());
            service.cartDeleteOne(dto);
            cnt ++;
        }

        return cnt;
    }



    //장바구니 팝업
    @GetMapping("/cartpopup")
    public String showCartPopupPage() {
        return "product/cartpopup";
    }
}
