package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CompanyDAO;
import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dao.PersonDAO;
import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
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

    @Autowired
    CompanyDAO companyDao;


    public void insertMemberOne(MemberDTO memberDto) {
        memberDao.insertOne(memberDto);
    }

    public void insertPersonOne(PersonDTO personDto) { personDao.insertOne(personDto); }

    public void insertCompanyOne(CompanyDTO companyDto) { companyDao.insertOne(companyDto); }

    public int idCheck(String memberId) {
        return memberDao.idCheck(memberId);
    }

    public int nicknameCheck(String nickname) { return personDao.nicknameCheck(nickname); }

    public String pwCheck(String memberId) { return memberDao.pwCheck(memberId);
    }
}
