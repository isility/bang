package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.CartDAO;
import kr.co.jhta.bang.finalproject.dto.CartDTO;
import kr.co.jhta.bang.finalproject.dto.KakaoPayApprovalDTO;
import kr.co.jhta.bang.finalproject.dto.KakaoPayReadyDTO;
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



    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyDTO KakaoPayReadyDTO;
    private KakaoPayApprovalDTO KakaoPayApprovalDTO;

    public String kakaoPayReady() {



        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "87a4d0fe3b84d27d45429f1c0674dfb7");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");

        List<CartDTO> CartDTOList = (List<CartDTO>) session.getAttribute("cartList");
        int cnt = 0;
        if (CartDTOList != null && !CartDTOList.isEmpty()) {
            CartDTO dto = CartDTOList.get(0);  // 첫 번째 항목을 가져온다
            String productName = dto.getProductName();
            for(CartDTO cdto : CartDTOList)
                cnt +=1;
            if(cnt == 0)
                params.add("item_name", productName);
            else
                params.add("item_name", productName +" 등 " +  cnt + "건");
            log.info("--------------------------------------상품이름" + productName +" 등 " +  cnt + "건");
        }else
            return "kakaoPaySuccessFail";

        List<CartDTO> cartList = (List<CartDTO>) session.getAttribute("cartList");
        int cartQuantity = 0;
        if (cartList != null && !cartList.isEmpty()) {
            for(CartDTO cdto : cartList){
                cartQuantity += cdto.getCartQuantity();
            }
            params.add("quantity", Integer.toString(cartQuantity));
        } else {
            return "kakaoPaySuccessFail";
        }

        params.add("total_amount", ""+session.getAttribute("totalPrice"));

        params.add("tax_free_amount", "100");
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

        return "/pay";

    }
    public KakaoPayApprovalDTO kakaoPayInfo(String pg_token) {

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
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", "2100");

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
