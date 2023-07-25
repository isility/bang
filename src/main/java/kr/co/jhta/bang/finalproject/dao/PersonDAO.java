package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonDAO {

    void insertOne(PersonDTO dto);
}
