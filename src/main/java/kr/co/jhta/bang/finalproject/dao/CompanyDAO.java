package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CompanyDAO {

    void insertOne(CompanyDTO dto);
}
