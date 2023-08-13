package kr.co.jhta.bang.finalproject.util;

import kr.co.jhta.bang.finalproject.dto.MemberDTO;
import kr.co.jhta.bang.finalproject.service.MemberUserDetails;
import lombok.Getter;

import java.util.Map;

@Getter
public class MemberAdapter extends MemberUserDetails {

    private MemberDTO memberDto;
    private Map<String, Object> attributes;

    public MemberAdapter() {
        // 기본 생성자
    }

    public MemberAdapter(MemberDTO memberDto) {
        super(memberDto);
        this.memberDto = memberDto;
    }

    public MemberAdapter(MemberDTO memberDto, Map<String, Object> attributes){
        super(memberDto, attributes);
        this.memberDto = memberDto;
        this.attributes = attributes;
    }
}
