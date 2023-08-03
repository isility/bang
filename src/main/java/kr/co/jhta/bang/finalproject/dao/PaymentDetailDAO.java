package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentDetailDAO {
    List<PaymentDetailDTO> getFourPaymentDetail();
}
