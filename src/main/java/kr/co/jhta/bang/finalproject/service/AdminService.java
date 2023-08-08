package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.AdminDAO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminDAO dao;
    public List<PaymentDetailDTO> getFourTable(){
//        log.info("service : {}",dao.getFourPaymentDetail());
        return dao.getFourPaymentDetail();
    }

    public List<PaymentDetailDTO> getPriceYear(){

        return dao.getPriceYear();
    }

    public List<PaymentDetailDTO> getSalesAvgMonth(){
        return dao.getSalesAvgMonth();
    }

    public List<QnaDTO> getDashboardQna(){
        return dao.getDashboardQna();
    }

    public List<PaymentDetailDTO> getPaging(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getPaymentPaging(se);
    }

    public int getTotal(){
        return dao.getTotal();
    }

    public void updatePaymentList(PaymentDetailDTO dto){
        dao.updatePaymentList(dto);
    }

    public List<MemberDTO> getMemberPaging(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getMemberPaging(se);
    }

    public List<MemberDTO> memberDetail(String member_id){

        return dao.memberDetail(member_id);
    }

    public void memberDelete(String member_id) {
        dao.memberDelete(member_id);
    }
}
