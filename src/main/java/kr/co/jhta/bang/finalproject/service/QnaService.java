package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dto.QnaDTO;

import java.util.List;

public interface QnaService {

    public List<QnaDTO> selectAll();

    public QnaDTO selectOne(int qnaNumber);

}
