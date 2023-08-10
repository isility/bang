package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.CartQuantityModifyDTO;
import kr.co.jhta.bang.finalproject.dto.ImageDTO;
import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    ProductListDAO dao;

    @Autowired
    CartDAO cartDAO;

    CartQuantityModifyDTO QuantityDTO;

    public List<ProductListDTO> selectAll(int categoryNumber){
        return dao.selectAll(categoryNumber);
    }

    public int getTotal(int categoryNumber) {
        return dao.getTotal(categoryNumber);
    }

    public ProductListDTO selectOne(int productNumber){
        return dao.selectOne(productNumber);
    }

    public List<ImageDTO> selectList(int productNumber){
        return dao.selectList(productNumber);
    }

    public void cartQuantityUpdateOne(int quan,int pno,String id){
        QuantityDTO = new CartQuantityModifyDTO(quan,pno,id);
        log.info(id);
        cartDAO.updateQuantityOne(QuantityDTO);
    }
    public int allPrice(String id){
        int totalPrice = 0;
            for(CartDTO dto : cartDAO.allPrice(id))
                totalPrice += dto.getProductPrice() * dto.getCartQuantity();
        return totalPrice;
    }

    public void cartDeleteOne(CartQuantityModifyDTO dto){

        cartDAO.deleteOne(dto);
    }

}
