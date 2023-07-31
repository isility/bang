package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.QnaDAO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import kr.co.jhta.bang.finalproject.dto.StartEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaAwsService implements QnaService{
    @Autowired
    private QnaDAO dao;

    @Override
    public List<QnaDTO> selectAll(int startNo, int endNo) {

        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getAll(se);
    }

    @Override
    public int getTotal() {
        return dao.getTotal();
    }

   /* @Override
    public List<QnaDTO> selectAll() {
        return dao.getAll();
    }*/

    @Override
    public QnaDTO selectOne(int qnaNumber) {
        return dao.selectOne(qnaNumber);
    }

    @Override
    public void addOne(QnaDTO dto) {
        dao.insertOne(dto);
    }

    @Override
    public void modifyOne(QnaDTO dto) {
        dao.updateOne(dto);
    }

    @Override
    public void removeOne(int qnaNumber) {
        dao.deleteOne(qnaNumber);
    }
}
