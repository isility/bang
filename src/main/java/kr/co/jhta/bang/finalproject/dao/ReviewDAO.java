package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDAO {


    List<ReviewDTO> getAll();
//    List<ReviewDTO> findAllReply(StartEnd se);
    ReviewDTO findByReply_number(int replyNumber);
    int getTotal();

//    List<ReviewDTO> reviewList(int pageNumber, int contentsNumber);
//    int reviewCount();
//    List<ReviewDTO> list(int pageNumber, int contentsNumber);


}
