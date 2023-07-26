package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewDAO {


    List<ReviewDTO> getAll();
    List<ReviewDTO> findAllReply(StartEnd se);
    ReviewDTO findByReply_number(int replyNumber);
    int getTotal();

}
