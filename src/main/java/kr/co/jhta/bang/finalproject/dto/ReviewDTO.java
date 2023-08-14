package kr.co.jhta.bang.finalproject.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewDTO {

    private int replyNumber;
    private String replyContents;
    private String replyWriter; // 댓글 작성자 정보( -> member type )
    private String replyDate;
    private int replyRef; // 댓글 그룹 번호
    private int replyStep; // 댓글의 계층 위치
    private int replyOrder; // 댓글의 순서
    private int replyAnswerNumber; // 대댓글 수
    private int replyParentNumber; // 부모 댓글 ID ( 대댓글일 경우 )
    private int replyScore; // 리뷰점수
    private int productNumber; // 리뷰가 작성 될 상품 번호
    private String member_id;
    private String paymentDetailStatus;
    private int paymentDetailNumber;
    private String productName;
//    private int productDetailNumber;
//    private String productName;
//    private int productCount;
//    private String paymentDetailStatus;
//    private int paymentDetailNumber;
//    private String productReplyImg;
//    private String productReplyImgPath;
}
