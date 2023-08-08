package kr.co.jhta.bang.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {

    private int serviceNumber;
    private String serviceContents;
    private String serviceEmail;
    private String serviceType;
    private int paymentDetailNumber;
}
