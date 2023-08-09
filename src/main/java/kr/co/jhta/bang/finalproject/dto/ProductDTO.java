package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private int productNumber;
    private String productName;
    private int productPrice;
    private int productStock;
    private String productDetail;
    private String productRegist;
    private int categoryNumber;

}
