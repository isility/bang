package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentDetailDAO {
    List<PaymentDetailDTO> getFourPaymentDetail();

    List<PaymentDetailDTO> getPriceYear();

    //int getSalesThisMonth();
    List<PaymentDetailDTO> getSalesAvgMonth();

    List<QnaDTO> getDashboardQna();


}
