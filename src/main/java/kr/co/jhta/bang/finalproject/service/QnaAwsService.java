package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.QnaDAO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaAwsService implements QnaService{
    @Autowired
    private QnaDAO dao;


    @Override
    public List<QnaDTO> selectAll() {
        return dao.getAll();
    }

    @Override
    public QnaDTO selectOne(int qnaNumber) {
        return dao.selectOne(qnaNumber);
    }
}
