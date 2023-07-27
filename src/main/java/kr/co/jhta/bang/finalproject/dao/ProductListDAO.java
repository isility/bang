package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductListDAO {

    //상품리스트
    public List<ProductListDTO> selectAll(int cno);

    //카테고리별 총 상품 수
    public int getTotal(int cno);

    //상세 페이지
    public ProductListDTO selectOne(int pno);


}
