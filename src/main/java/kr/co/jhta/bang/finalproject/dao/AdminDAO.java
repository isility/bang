package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminDAO {
    List<PaymentDetailDTO> getFourPaymentDetail();

    List<PaymentDetailDTO> getPriceYear();

    //int getSalesThisMonth();
    List<PaymentDetailDTO> getSalesAvgMonth();

    List<QnaDTO> getDashboardQna();

    List<PaymentDetailDTO> getPaymentPaging(StartEnd se);

    int getTotal();

    void updatePaymentList(PaymentDetailDTO dto);

    List<MemberDTO> getMemberPaging(StartEnd se);

    void memberDelete(String member_id);

    List<MemberDTO> memberDetail(String member_id);
}
