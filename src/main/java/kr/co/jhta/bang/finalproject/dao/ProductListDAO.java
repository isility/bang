package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductListDAO {

    //상품리스트
    public List<ProductDTO> selectAll();

    //카테고리 리스트
    public ProductDTO selectOne(int no);
}
