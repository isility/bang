package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CompanyDAO;
import kr.co.jhta.bang.finalproject.dao.MemberDAO;
import kr.co.jhta.bang.finalproject.dao.PersonDAO;
import kr.co.jhta.bang.finalproject.dto.CompanyDTO;
import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberService {
    @Autowired
    MemberDAO memberDao;

    @Autowired
    PersonDAO personDao;

    @Autowired
    CompanyDAO companyDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MemberDTO selectMemberDetail(String memberId) { return memberDao.selectOneDetail(memberId);}


    public void insertMemberOne(MemberDTO memberDto) {
        memberDao.insertOne(memberDto);
    }

    public void insertPersonOne(PersonDTO personDto) { personDao.insertOne(personDto); }

    public void insertCompanyOne(CompanyDTO companyDto) { companyDao.insertOne(companyDto); }

    public int idCheck(String memberId) {
        return memberDao.idCheck(memberId);
    }

    public int idSocialCheck(String socialMemberId) { return memberDao.idSocialCheck(socialMemberId); }

    public int nicknameCheck(String nickname) { return personDao.nicknameCheck(nickname); }

    public String pwCheck(String memberId) { return memberDao.pwCheck(memberId); }


    public int emailCheck(String memberEmail) { return memberDao.emailCheck(memberEmail); }


    public MemberDTO login(final String loginId, final String password) {

        // 1. 회원 정보 및 비밀번호 조회
        MemberDTO member = memberDao.selectOne(loginId);
        String encodedPassword = (member == null) ? "" : member.getMember_pw();

        // 2. 회원 정보 및 비밀번호 체크
        if (member == null || member.getMember_pw() != passwordEncoder.encode(password) ) {
            log.info("회원이 아니거나, 비밀번호가 다름.");
            return null;
        }

        // 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
        return member;
    }

    public List<MemberDTO> findByidEmail(String name, String email) { return memberDao.findByidEmail(name, email); }
}
