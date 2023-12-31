package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDAO {
    List<ReviewDTO> getAll(); // reply_step = 0인 리뷰들 가져오는 select
    List<ReviewDTO> findAllReply(StartEnd se); // 페이징
    ReviewDTO findByReply_number(int replyNumber); // replyNumber로 reviewDetail로 연결하는 select
    int getTotal();
    void writeReply(ReviewDTO dto); // 리뷰 작성하는 insert
    void deleteReview(int replyNumber);
    void modifyReview(ReviewDTO dto);
    void setReplyFirst(ReviewDTO dto); // 최초 댓글 작성
    List<ReviewDTO> findAllByReplyRef(int replyNumber); // 댓글들만 가져옴
    List<ReviewDTO> getOneByMemberId(String member_id);
    String getProductNumberByName(String member_id);
    String getProductName(int productNumber);
    List<ReviewDTO> getReplyByProductNumber(int productNumber);

    int getProductNumber(String productName);

}
