package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.ProductListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IndexDAO {

    public List<ProductListDTO> selectAllSpeaker();
    public List<ProductListDTO> selectAllEarphone();
    public List<ProductListDTO> selectAllHeadphone();


}
