package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ReviewDAO;
import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewDAO dao;
    public List<ReviewDTO> findAllReply(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getAll();
    }
    public List<ReviewDTO> getAll() { return dao.getAll(); }
    public ReviewDTO findByReply_number(int replyNumber) {
        return dao.findByReply_number(replyNumber);
    }

    public int getTotal(){
        return dao.getTotal();
    }


}
