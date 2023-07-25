package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private int reply_number;
    private String reply_contents;
    private String reply_writer; // 댓글 작성자 정보( -> member type )
    private String reply_date;
    private int reply_ref; // 댓글 그룹 번호
    private int reply_step; // 댓글의 계층 위치
    private int reply_order; // 댓글의 순서
    private int reply_answer_number; // 대댓글 수
    private int reply_parent_number; // 부모 댓글 ID ( 대댓글일 경우 )
    private int reply_score; // 리뷰점수
    private int product_number; // 리뷰가 작성 될 상품 번호
}
