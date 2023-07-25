package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Getter @Setter //@Data 로 쓰는 건 위험
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private int product_number;
    private String product_name;
    private int product_price;
    private int product_stock;
    private String product_detail;
    private String product_regist;
    private int category_number;
}
