package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.NoticeDAO;
import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeAwsService implements NoticeService{

    @Autowired
    private NoticeDAO dao;

    @Override
    public List<NoticeDTO> selectAll() {
        return dao.getAll();

    }

    @Override
    public NoticeDTO selectOne(int noticeNumber) {
        return dao.selectOne(noticeNumber);
    }

    @Override
    public void addOne(NoticeDTO dto) {
        dao.insertOne(dto);
    }

    @Override
    public void modifyOne(NoticeDTO dto) {
        dao.updateOne(dto);
    }

    @Override
    public void removeOne(int noticeNumber) {
        dao.deleteOne(noticeNumber);
    }
}
