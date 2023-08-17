package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentDAO {
    void insertOne(PaymentDTO dto);

    int selectTop();

    void detailInsertOne(PaymentDTO dto);

    List<PaymentDTO> orderList(String id);

    List<PaymentDTO> orderProductList(PaymentDTO dto);

    void deleteCart(PaymentDTO paymentDTO);
}
