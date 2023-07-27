package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Getter @Setter //@Data 로 쓰는 건 위험
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
