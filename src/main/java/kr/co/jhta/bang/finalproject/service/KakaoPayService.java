package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dao.PaymentDAO;
import kr.co.jhta.bang.finalproject.dto.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
public class KakaoPayService {

    @Autowired
    @Getter
    HttpSession session;

    @Autowired
    PaymentDAO paymentDAO;

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyDTO KakaoPayReadyDTO;
    private KakaoPayApprovalDTO KakaoPayApprovalDTO;

    @Setter
    PaymentDTO paymentDTO;

    MemberDTO memberDTO;

    public String kakaoPayReady() {

        RestTemplate restTemplate = new RestTemplate();
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");


        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "87a4d0fe3b84d27d45429f1c0674dfb7");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", Integer.toString(paymentDAO.selectTop() + 1));
        params.add("partner_user_id", memberDTO.getMember_id());

        List<CartDTO> CartDTOList = (List<CartDTO>) session.getAttribute("cartList");
        int cnt = 0;
        if (CartDTOList != null && !CartDTOList.isEmpty()) {
            CartDTO cartdto = CartDTOList.get(0);  // 첫 번째 항목을 가져온다
            String productName = cartdto.getProductName();
            for (CartDTO cdto : CartDTOList)
                cnt += 1;
            if (cnt == 1)
                params.add("item_name", productName);
            else
                params.add("item_name", productName + " 등 " + cnt + "건");
            log.info("--------------------------------------상품이름" + productName + " 등 " + cnt + "건");
        } else
            return "kakaoPaySuccessFail";

        List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cartList");
        int cartQuantity = 0;
        if (cartList != null && !cartList.isEmpty()) {
            for (CartDTO cdto : cartList) {
                cartQuantity += cdto.getCartQuantity();
            }
            params.add("quantity", Integer.toString(cartQuantity));
        } else {
            return "kakaoPaySuccessFail";
        }
        int tax = (int) ((int) session.getAttribute("totalPrice") * 0.1);

        params.add("total_amount", Integer.toString((int) session.getAttribute("totalPrice")));
        params.add("tax_free_amount", "" + tax);
        params.add("approval_url", "http://localhost:8082/payment/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8082/payment/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8082/payment/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            KakaoPayReadyDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyDTO.class);

            return KakaoPayReadyDTO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "kakaoPaySuccessFail";

    }

    public KakaoPayApprovalDTO kakaoPayInfo(String pg_token) {

        paymentDTO = new PaymentDTO();

        memberDTO = (MemberDTO) session.getAttribute("member");
        log.info("멤버 dto : " + memberDTO);
        log.info("id : " + memberDTO.getMember_id());
        paymentDTO.setMemberID(memberDTO.getMember_id());
        paymentDTO.setPaymentPostal(memberDTO.getMember_postal());
        paymentDTO.setPaymentAddress1(memberDTO.getMember_address1());
        paymentDTO.setPaymentAddress2(memberDTO.getMember_address2());
        paymentDTO.setPaymentName(memberDTO.getMember_name());
        paymentDTO.setPaymentPhone(memberDTO.getMember_phone());
        paymentDTO.setPaymentMethod(1);

        paymentDAO.insertOne(paymentDTO);

        log.info("주문목록 생성");

        paymentDTO.setPaymentNumber(paymentDAO.selectTop());
        paymentDTO.setProduct_price((int) session.getAttribute("totalPrice"));
        paymentDTO.setPayment_detail_status("입금확인");

        List<CartDTO> CartDTOList = (List<CartDTO>) session.getAttribute("cartList");
        int cnt = 0;
        if (CartDTOList != null && !CartDTOList.isEmpty()) {
            for (CartDTO cdto : CartDTOList)
                cnt += 1;
            paymentDTO.setProduct_count(cnt);

            for (CartDTO cdto : CartDTOList) {
                paymentDTO.setProduct_number(cdto.getProductNumber());
                paymentDAO.detailInsertOne(paymentDTO);
                log.info("주문목록 생성2");
            }
        }

        log.info("주문목록 생성3");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "87a4d0fe3b84d27d45429f1c0674dfb7");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");


        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", KakaoPayReadyDTO.getTid());
        params.add("partner_order_id", Integer.toString(paymentDAO.selectTop() + 1));     //주문번호
        params.add("partner_user_id", memberDTO.getMember_id());    //유저아이디
        params.add("pg_token", pg_token);
        params.add("total_amount", session.getAttribute("totalPrice") + "");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            KakaoPayApprovalDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalDTO.class);


            return KakaoPayApprovalDTO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
