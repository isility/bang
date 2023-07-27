package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeDAO {

    List<NoticeDTO> getAll();

    NoticeDTO selectOne(int noticeNumber);

    void insertOne(NoticeDTO dto);

    void updateOne(NoticeDTO dto);

    void deleteOne(int noticeNumber);
}
