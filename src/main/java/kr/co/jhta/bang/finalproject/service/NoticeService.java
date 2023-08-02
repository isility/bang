package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.NoticeDTO;
import kr.co.jhta.bang.finalproject.dto.QnaDTO;

import java.util.List;

public interface NoticeService {

    public List<NoticeDTO> selectAll(int startNo, int endNo);

    public int getTotal();

    public NoticeDTO selectOne(int noticeNumber);

    public void addOne(NoticeDTO dto);

    public void modifyOne(NoticeDTO dto);

    public void removeOne(int noticeNumber);
}
