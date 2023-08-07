package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QnaDAO {

    List<QnaDTO> getAll(StartEnd se);

    int getTotal();

    QnaDTO selectOne(int qnaNumber);

    void insertOne(QnaDTO dto);

    void updateOne(QnaDTO dto);

    void deleteOne(int qnaNumber);


}
