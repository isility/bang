package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private int notice_number;
    private String notice_title;
    private String notice_contents;
    private String notice_writer;
    private String notice_date;
    private String member_id;

}
