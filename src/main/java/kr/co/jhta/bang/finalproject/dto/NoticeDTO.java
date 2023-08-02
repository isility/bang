package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private int noticeNumber;
    private String noticeTitle;
    private String noticeContents;
    private String noticeWriter;
    private String noticeDate;
    private String memberId;

}
