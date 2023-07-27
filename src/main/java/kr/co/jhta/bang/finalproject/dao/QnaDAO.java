package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QnaDAO {

    List<QnaDTO> getAll();

    QnaDTO selectOne(int qnaNumber);

    void insertOne(QnaDTO dto);

    void updateOne(QnaDTO dto);

    void deleteOne(int qnaNumber);
}
