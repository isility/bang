package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO {
    private int paymentDetailNumber;
    private int productCount;
    private int productPrice;
    private String paymentDetailStatus;
    private int productNumber;
    private int paymentNumber;
    private String productName;
    private String paymentDate;
    private int totalProductPrice;
    private int averageMonth;
    private int averageYear;
    private String month;
    private String year;


}
