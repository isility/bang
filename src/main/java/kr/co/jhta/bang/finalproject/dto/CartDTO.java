package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    int cartNumber;
    int productNumber;
    String memberID;
    int cartQuantity;
    String productName;
    String img;
    String memberAddress1;
    String memberAddress2;
    String memberName;
    String memberPhone;
    String memberPostal;
    String memberEmail;
}