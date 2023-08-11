package kr.co.jhta.bang.finalproject.dao;

import kr.co.jhta.bang.finalproject.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminDAO {
    List<PaymentDetailDTO> getFourPaymentDetail();

    List<PaymentDetailDTO> getPriceYear();

    //int getSalesThisMonth();
    List<PaymentDetailDTO> getSalesAvgMonth();

    List<QnaDTO> getDashboardQna();

    List<PaymentDetailDTO> getPaymentPaging(StartEnd se);

    int getTotalReply();
    int getTotalPaymentDetail();
    int getTotalMember();
    int getTotalService();
    int getTotalProduct();

    void updatePaymentList(PaymentDetailDTO dto);

    List<MemberDTO> getMemberPaging(StartEnd se);

    void memberDelete(String member_id);

    MemberDTO memberDetail(String member_id);

    void updateMemberDetail(MemberDTO dto);

    List<ServiceDTO> getServicePaging(StartEnd se);

    void updateServiceList(ServiceDTO dto);

    void serviceDelete(int serviceNumber);

    List<ProductDTO> getProductPaging(StartEnd se);

    void updateProductList(ProductDTO dto);

    void productDelete(int productNumber);

    ProductDTO productDetail(int productNumber);

    void updateProductModify(ProductDTO dto);

    List<MemberDTO> memberCount();

    int getTotalNotice();

    List<NoticeDTO> getAllNotice(StartEnd se);

    NoticeDTO selectOneNotice(int noticeNumber);

    void insertOneNotice(NoticeDTO dto);

    void updateOneNotice(NoticeDTO dto);

    void deleteOneNotice(int noticeNumber);

    int getTotalReview();

    List<ReviewDTO> findAllReply(StartEnd se);

    ReviewDTO findByReply_number(int replyNumber);

    void modifyReview(ReviewDTO dto);

    void deleteReview(int replyNumber);
}
