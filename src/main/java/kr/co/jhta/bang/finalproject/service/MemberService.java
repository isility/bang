package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dao.PersonDAO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDAO memberDao;
    @Autowired
    PersonDAO personDao;


    public void insertMemberOne(MemberDTO memberDto) {
        memberDao.insertOne(memberDto);
    }

    public void insertPersonOne(PersonDTO personDto) { personDao.insertOne(personDto); }

    public int idCheck(MemberDTO dto) {
        return memberDao.idCheck(dto);
    }
}
