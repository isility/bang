package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDAO {
    void insertOne(MemberDTO memberDto);

    int idCheck(String memberId);

    String pwCheck(String memberId);

    MemberDTO selectOne(String id);

    MemberDTO selectOneDetail(String memberId);

    int emailCheck(String memberEmail);

    int idSocialCheck(String socialMemberId);

    List<MemberDTO> findByidEmail(@Param("member_name") String memberName, @Param("member_email") String memberEmail);
}
