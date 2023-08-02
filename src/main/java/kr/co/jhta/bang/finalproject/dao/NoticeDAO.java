package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeDAO {

    List<NoticeDTO> getAll(StartEnd se);

    int getTotal();

    NoticeDTO selectOne(int noticeNumber);

    void insertOne(NoticeDTO dto);

    void updateOne(NoticeDTO dto);

    void deleteOne(int noticeNumber);
}
