package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dao.PaymentDAO;
import kr.co.jhta.bang.finalproject.dao.ProductListDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.PaymentDTO;
import kr.co.jhta.bang.finalproject.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    CartDAO CartDAO;

    @Autowired
    PaymentDAO paymentDAO;

    public List<CartDTO> cartlist(String id){
        return CartDAO.selectAll(id);
    }

    public CartDTO selectList(CartDTO dto){
        return CartDAO.selectList(dto);
    }

    public List<PaymentDTO> orderList(String id){
            return paymentDAO.orderList(id);
    }

    public List<PaymentDTO> orderProductList(PaymentDTO dto){
        return paymentDAO.orderProductList(dto);
    }

}
