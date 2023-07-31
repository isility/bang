package kr.co.jhta.bang.finalproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CompanyDTO {
    String member_id;
    String company_ceoname;
    String company_registnumber;
    String company_account;
    String company_managername;
    String company_managerphone;
    String company_registfile;
    String company_bank;
}
