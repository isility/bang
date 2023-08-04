package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.PaymentDetailDAO;
import kr.co.jhta.bang.finalproject.dto.PaymentDetailDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentDetailService {

    @Autowired
    private PaymentDetailDAO dao;

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

    

//    public Integer getSalesThisMonth(){
//
//        Integer sales = dao.getSalesThisMonth();
//        return sales;
//    }


}
