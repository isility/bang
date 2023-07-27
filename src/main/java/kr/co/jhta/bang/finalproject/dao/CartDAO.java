package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartDAO {
    List<CartDTO> selectAll(String memberID);
}
