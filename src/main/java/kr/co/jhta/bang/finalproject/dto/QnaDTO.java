package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private int qna_number;
    private String qna_title;
    private String qna_contents;
    private String qna_writer;
    private String qna_date;
    private int qna_ref;
    private int qna_step;
    private int qna_ref_order;
    private int qna_answer_number;
    private int qna_parents_number;
    private String qna_type;
    private int qna_status;
    private String qna_secret;
    private String member_id;
}
