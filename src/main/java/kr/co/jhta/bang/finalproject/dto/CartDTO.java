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
    int cartQuantity;
    String productName;
    String img;
    int productPrice;
}