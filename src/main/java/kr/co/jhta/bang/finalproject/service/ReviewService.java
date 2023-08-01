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
    public void writeReply(ReviewDTO dto){
        dao.writeReply(dto);
    }

    // review 삭제
    public void deleteReview(int replyNumber){
        dao.deleteReview(replyNumber);
    }

    public void modifyReview(ReviewDTO reviewDTO)  {dao.modifyReview(reviewDTO);}


    public List<ReviewDTO> firstCommentsSave(ReviewDTO reviewDTO) {
        log.info("reviewDTO: {}", reviewDTO);
        dao.setReplyFirstStep(reviewDTO);

        // 저장 결과를 얻는 로직 (dao에 따라 다를 수 있음)
        List<ReviewDTO> savedReviews = dao.getAll();

        return savedReviews;
    }





}
