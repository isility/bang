package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDAO dao;

    public void insertOne(MemberDTO dto) {
        dao.insertOne(dto);
    }
}
