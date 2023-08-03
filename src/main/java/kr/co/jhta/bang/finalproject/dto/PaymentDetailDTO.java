package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentDetailDTO {
    private int paymentDetailNumber;
    private int productCount;
    private int productPrice;
    private String paymentDetailStatus;
    private int productNumber;
    private int paymentNumber;
    private String productName;
}
