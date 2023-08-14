package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ReviewDAO;
import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewService {
    @Autowired
    private ReviewDAO dao; // 리뷰 dao

    // 페이징
    public List<ReviewDTO> findAllReply(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.findAllReply(se);
    }
    // 모든 리뷰글 조회
    public List<ReviewDTO> getAll() { return dao.getAll(); }

    // 리뷰번호로 리뷰 하나 조회
    public ReviewDTO findByReply_number(int replyNumber) {
        return dao.findByReply_number(replyNumber);
    }


    public int getTotal(){
        return dao.getTotal();
    }


    // review 작성 로직
    public int writeReply(ReviewDTO dto){
        dao.writeReply(dto);
        return dto.getReplyNumber();
    }

    // review 삭제
    public void deleteReview(int replyNumber){
        dao.deleteReview(replyNumber);
    }

    public void modifyReview(ReviewDTO reviewDTO)  {dao.modifyReview(reviewDTO);}


    public List<ReviewDTO> firstCommentsSave(ReviewDTO reviewDTO) {
        log.info("reviewDTO: {}", reviewDTO);
        dao.setReplyFirst(reviewDTO);

        List<ReviewDTO> savedReviews = dao.findAllByReplyRef(reviewDTO.getReplyNumber());

        return savedReviews;
    }

    public List<ReviewDTO> findAllByReplyRef(int refNumber){
        return dao.findAllByReplyRef(refNumber);
    }

    public List<ReviewDTO> getOneByMemberId(String member_id){
        return dao.getOneByMemberId(member_id);
    }

    public int getProductNumberByName(String productName){
        return dao.getProductNumberByName(productName);
    }

    public String getProductName(int productNumber){
        return dao.getProductName(productNumber);
    }
}
