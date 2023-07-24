package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    void insertOne(MemberDTO dto);
}
