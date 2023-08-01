package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;

import java.util.List;

public interface QnaService {

    public List<QnaDTO> selectAll(int startNo, int endNo);

    public int getTotal();

 /*   public List<QnaDTO> selectAll();*/

    public QnaDTO selectOne(int qnaNumber);

    public void addOne(QnaDTO dto);

    public void modifyOne(QnaDTO dto);

    public void removeOne(int qnaNumber);
}
