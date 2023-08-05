package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    void insertOne(MemberDTO memberDto);

    int idCheck(String memberId);

    String pwCheck(String memberId);

    MemberDTO selectOne(String id);

    MemberDTO selectOneDetail(String memberId);
}
