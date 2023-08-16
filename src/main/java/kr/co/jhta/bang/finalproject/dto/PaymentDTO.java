package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    int paymentNumber;
    String paymentPostal;
    String paymentAddress1;
    String paymentAddress2;
    String paymentName;
    String paymentPhone;
    int paymentMethod;
    String memberID;
    int productCount;
    int productPrice;
    String paymentDetailStatus;
    int productNumber;
}
