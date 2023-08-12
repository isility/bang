package kr.co.jhta.bang.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private String authRandomCode;

    public void createCode() {
        Random random = new Random();

        String code1 = random.nextInt(10)+"";
        String code2 = random.nextInt(10)+"";
        String code3 = random.nextInt(10)+"";
        String code4 = random.nextInt(10)+"";
        String code5 = random.nextInt(10)+"";
        String code6 = random.nextInt(10)+"";

        authRandomCode = code1 + code2 + code3 + code4 + code5 + code6;
    }

    public String sendEmail(String id, String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

        createCode();

        /* email 양식 */
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        sb.append("<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Bang & Oops Email</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "  </head>");
        sb.append("<body>\n" +
                "    <!-- 바깥 영역(배경) -->\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgColor=\"#F4F5F7\" style=\"padding: 20px 16px 82px; color: #191919; font-family: 'Noto Sans KR', sans-serif;\" class=\"wrapper\">\n" +
                "    <tbody style=\"display: block; max-width: 600px;\">\n" +
                "      <tr width=\"100%\" style=\"display: block;\">\n" +
                "        <td width=\"100%\" style=\"display: block;\">\n" +
                "          <!-- 본문 -->\n" +
                "          <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgColor=\"#FFFFFF\" style=\"display: inline-block; padding: 32px; text-align: left; border-top: 3px solid #D1C286; border-collapse: collapse;\" class=\"container\">\n" +
                "            <tbody style=\"display: block;\">\n" +
                "              <tr>\n" +
                "                <td style=\"padding-bottom: 32px; font-size: 20px; font-weight: bold;\">\n" +
                "                    Bang & Oops\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr style=\"display: block;\">\n" +
                "                <td style=\"display: block; padding-bottom: 16px; font-size: 20px; font-weight: bold;\">\n" +
                "                  안녕하세요, " + id + " 님! </td>\n" +
                "                </tr>\n" +
                "                <tr></tr>\n" +
                "              <!-- 본문 컨텐츠 영역 -->\n" +
                "              <tr width=\"100%\" style=\"display: block; margin-bottom: 32px;\">\n" +
                "                <td width=\"100%\" style=\"display: block;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgColor=\"#F8F9FA\" class=\"content\">\n" +
                "                    <tbody>\n" +
                "                      <tr style=\"display: block; margin-bottom: 32px;\"></tr>\n" +
                "                      <tr>하단 인증번호를 확인해주세요!</tr>\n" +
                "                      <tr ></tr>\n" +
                "                      <tr>\n" +
                "                        <td style=\"font-size: 20px; font-weight: bold;\">\n" +
                "                            인증번호 : " + authRandomCode + "</td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr></tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding-bottom: 24px; color: #A7A7A7; font-size: 12px; line-height: 20px;\">© 2023 BANG & OOPS Co., Ltd. All Rights Reserved.</td>\n" +
                "              </tr>\n" +
                "              <tr width=\"100%\" style=\"display:block; padding-top: 24px; border-top: 1px solid #e9e9e9;\">\n" +
                "                <td style=\"position: relative;\">\n" +
                "                  <img height=\"16\" style=\"vertical-align: middle; display: inline-block; padding: 0 5px 0 0;\" src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGyO4j7eZL2jg1oDP5C3qk86GMsdBY3kgv9g&usqp=CAU' />\n" +
                "                  <img height=\"10\" style=\"display: inline-block; border-left: 1px solid #E9E9E9; padding: 0 8px;\" src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQa4wVpzBeALGm-D5k881IZXU_kjA3dFwwzA&usqp=CAU' />\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  </body>\n" +
                "</html>");

        helper.setTo(email);
        helper.setFrom(new InternetAddress("bang.oops.official@gmail.com", "BANG & OOPS 고객센터"));
        helper.setSubject("본인확인 인증번호 입니다.");
        helper.setText(sb.toString(), true);

        javaMailSender.send(message);

        log.info("서비스 단의 인증번호 : " + authRandomCode);

        return authRandomCode;
    }


    public String sendDetailIdEmail(String id, String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");

        /* email 양식 */
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        sb.append("<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <title>Bang & Oops Email</title>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "  </head>");
        sb.append("<body>\n" +
                "    <!-- 바깥 영역(배경) -->\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgColor=\"#F4F5F7\" style=\"padding: 20px 16px 82px; color: #191919; font-family: 'Noto Sans KR', sans-serif;\" class=\"wrapper\">\n" +
                "    <tbody style=\"display: block; max-width: 600px;\">\n" +
                "      <tr width=\"100%\" style=\"display: block;\">\n" +
                "        <td width=\"100%\" style=\"display: block;\">\n" +
                "          <!-- 본문 -->\n" +
                "          <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgColor=\"#FFFFFF\" style=\"display: inline-block; padding: 32px; text-align: left; border-top: 3px solid #D1C286; border-collapse: collapse;\" class=\"container\">\n" +
                "            <tbody style=\"display: block;\">\n" +
                "              <tr>\n" +
                "                <td style=\"padding-bottom: 32px; font-size: 20px; font-weight: bold;\">\n" +
                "                    Bang & Oops\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr style=\"display: block;\">\n" +
                "                <td style=\"display: block; padding-bottom: 16px; font-size: 20px; font-weight: bold;\">\n" +
                "                  요청하신 ID 전체 확인하기 입니다.  </td>\n" +
                "                </tr>\n" +
                "                <tr></tr>\n" +
                "              <!-- 본문 컨텐츠 영역 -->\n" +
                "              <tr width=\"100%\" style=\"display: block; margin-bottom: 32px;\">\n" +
                "                <td width=\"100%\" style=\"display: block;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgColor=\"#F8F9FA\" class=\"content\">\n" +
                "                    <tbody>\n" +
                "                      <tr style=\"display: block; margin-bottom: 32px;\"></tr>\n" +
                "                      <tr>하단 내용은 회원님의 전체 ID 입니다. 로그인을 진행해주세요!</tr>\n" +
                "                      <tr ></tr>\n" +
                "                      <tr>\n" +
                "                        <td style=\"font-size: 20px; font-weight: bold;\">\n" +
                "                            전체 ID : " + id + "</td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr></tr>\n" +
                "              <tr>\n" +
                "                <td style=\"padding-bottom: 24px; color: #A7A7A7; font-size: 12px; line-height: 20px;\">© 2023 BANG & OOPS Co., Ltd. All Rights Reserved.</td>\n" +
                "              </tr>\n" +
                "              <tr width=\"100%\" style=\"display:block; padding-top: 24px; border-top: 1px solid #e9e9e9;\">\n" +
                "                <td style=\"position: relative;\">\n" +
                "                  <img height=\"16\" style=\"vertical-align: middle; display: inline-block; padding: 0 5px 0 0;\" src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGyO4j7eZL2jg1oDP5C3qk86GMsdBY3kgv9g&usqp=CAU' />\n" +
                "                  <img height=\"10\" style=\"display: inline-block; border-left: 1px solid #E9E9E9; padding: 0 8px;\" src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQa4wVpzBeALGm-D5k881IZXU_kjA3dFwwzA&usqp=CAU' />\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  </body>\n" +
                "</html>");

        helper.setTo(email);
        helper.setFrom(new InternetAddress("bang.oops.official@gmail.com", "BANG & OOPS 고객센터"));
        helper.setSubject("ID 전체 확인하기 입니다.");
        helper.setText(sb.toString(), true);

        javaMailSender.send(message);

        return "success";

    }
}
