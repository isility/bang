package kr.co.jhta.bang.finalproject.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Component
public class MemberDTO {
    String member_id;
    String member_pw;
    String member_token;
    String member_name;
    String member_phone;
    String member_postal;
    String member_address1;
    String member_address2;
    String member_email;
    int email_check;
    String member_insertdate;
    int member_type;
    String member_profile;
    int role_number;
    int grade_number;
    int platformtype_number;
}
