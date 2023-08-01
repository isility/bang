package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PersonDTO {
    String member_id;
    String person_birth;
    String person_nickname;
}
