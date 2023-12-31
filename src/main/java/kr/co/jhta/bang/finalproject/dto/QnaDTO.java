package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {

    private int qnaNumber;
    private String qnaTitle;
    private String qnaContents;
    private String qnaWriter;
    private String qnaDate;
    private int qnaRef;
    private int qnaStep;
    private int qnaRefOrder;
    private int qnaAnswerNumber;
    private int qnaParentNumber;
    private String qnaType;
    private int qnaStatus;
    private String qnaSecret;
    private String memberId;

}
