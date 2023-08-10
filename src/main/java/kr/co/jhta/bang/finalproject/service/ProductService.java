package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
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
        CartDTO dto = new CartDTO();
        dto.setCartQuantity(quan);
        dto.setProductNumber(pno);
        dto.setMemberID(id);

        cartDAO.updateQuantityOne(dto);
    }
    public int allPrice(String id){
        int totalPrice = 0;
            for(CartDTO dto : cartDAO.allPrice(id))
                totalPrice += dto.getProductPrice() * dto.getCartQuantity();
        return totalPrice;
    }

    public void cartDeleteOne(CartDTO dto){

        cartDAO.deleteOne(dto);
    }

    public void cartInsertOne(CartDTO dto) {
        List<CartDTO> list = cartDAO.selectAll(dto.getMemberID());

        boolean flag = false;
        for(CartDTO cdto : list) {
            if (cdto.getProductNumber() == dto.getProductNumber()) {
                flag = true;
            }
        }
        if(flag) {
            cartDAO.updateQuantityOne(dto);
            flag = false;
        }
        else {
            cartDAO.insertOne(dto);
        }
    }
}
