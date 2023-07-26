package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductListDAO {

    //상품리스트
    public List<ProductListDTO> selectAll(int category_number);

    public int getTotal(int category_number);

    //카테고리 리스트
    public ProductListDTO selectOne(int no);
}
