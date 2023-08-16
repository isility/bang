package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
    private int productNumber;
    private String imageSrc;
    private String productName;
    private int productPrice;
    private int categoryNumber;
    private int reviewCount;

}
