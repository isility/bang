package kr.co.jhta.bang.finalproject.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KakaoPayReadyDTO {
    private String tid, next_redirect_pc_url;
    private Date created_at;

}
