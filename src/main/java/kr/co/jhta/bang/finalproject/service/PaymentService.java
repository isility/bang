package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    CartDAO CartDAO;

    public List<CartDTO> cartlist(String id){
        return CartDAO.selectAll(id);
    }

    public CartDTO selectList(CartDTO dto){
        return CartDAO.selectList(dto);
    }


}
