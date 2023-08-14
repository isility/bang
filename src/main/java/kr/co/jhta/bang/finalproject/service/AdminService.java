package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.AdminDAO;
import kr.co.jhta.bang.finalproject.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    @Autowired
    private AdminDAO dao;
    public List<PaymentDetailDTO> getFourTable(){
//        log.info("service : {}",dao.getFourPaymentDetail());
        return dao.getFourPaymentDetail();
    }

    public List<PaymentDetailDTO> getPriceYear(){

        return dao.getPriceYear();
    }

    public List<PaymentDetailDTO> getSalesAvgMonth(){
        return dao.getSalesAvgMonth();
    }

    public List<QnaDTO> getDashboardQna(){
        return dao.getDashboardQna();
    }

    public List<PaymentDetailDTO> getPaging(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getPaymentPaging(se);
    }

    public int getTotalReply(){
        return dao.getTotalReply();
    }
    public int getTotalPaymentDetail(){
        return dao.getTotalPaymentDetail();
    }
    public int getTotalMember(){
        return dao.getTotalMember();
    }
    public int getTotalService(){
        return dao.getTotalService();
    }
    public int getTotalProduct(){
        return dao.getTotalProduct();
    }

    public void updatePaymentList(PaymentDetailDTO dto){
        dao.updatePaymentList(dto);
    }

    public List<MemberDTO> getMemberPaging(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getMemberPaging(se);
    }

    public MemberDTO memberDetail(String member_id){

        return dao.memberDetail(member_id);
    }
    public void memberDelete(String member_id) {
        dao.memberDelete(member_id);
    }

    public void updateMemberDetail(MemberDTO dto){dao.updateMemberDetail(dto);}

    public List<ServiceDTO> getServicePaging(int startNo, int endNo){
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getServicePaging(se);
    }

    public void updateServiceList(ServiceDTO dto){
        dao.updateServiceList(dto);
    }

    public void serviceDelete(int serviceNumber){
        dao.serviceDelete(serviceNumber);
    }

    public List<ProductDTO> getProductPaging(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getProductPaging(se);
    }

    public void updateProductList(ProductDTO dto) {
        dao.updateProductList(dto);
    }

    public void productDelete(int productNumber) {
        dao.productDelete(productNumber);
    }

    public ProductDTO productDetail(int productNumber) {
        return dao.productDetail(productNumber);
    }

    public void updateProductModify(ProductDTO dto) {
        dao.updateProductModify(dto);
    }

    public List<MemberDTO> memberCount(){
        return dao.memberCount();
    }



    public int getTotalNotice() {
        return  dao.getTotalNotice();
    }

    public List<NoticeDTO> selectAllNotice(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getAllNotice(se);
    }
    public NoticeDTO selectOneNotice(int noticeNumber) {
        return dao.selectOneNotice(noticeNumber);
    }

    public void modifyOneNotice(NoticeDTO dto) { dao.updateOneNotice(dto);
    }
    public void removeOneNotice(int noticeNumber) { dao.deleteOneNotice(noticeNumber);
    }

    public void addOneNotice(NoticeDTO dto) { dao.insertOneNotice(dto);
    }


    public int getTotalReview() { return dao.getTotalReview();
    }

    public List<ReviewDTO> findAllReply(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.findAllReply(se);
    }

    public ReviewDTO findByReply_number(int replyNumber) {
        return dao.findByReply_number(replyNumber);
    }

    public void modifyReview(ReviewDTO reviewDTO) {
        dao.modifyReview(reviewDTO);
    }

    public void deleteReview(int replyNumber) {
        dao.deleteReview(replyNumber);
    }

    public int getTotalQna() {
        return  dao.getTotalQna();
    }

    public List<QnaDTO> selectAllQna(int startNo, int endNo) {
        StartEnd se = new StartEnd(startNo, endNo);
        return dao.getAllQna(se);
    }

    public void addOneQna(QnaDTO dto) {
        dao.insertOneQna(dto);
    }

    public Object selectOneQna(int qnaNumber) {
        return dao.selectOneQna(qnaNumber);
    }

    public void modifyOneQna(QnaDTO dto) {
        dao.updateOneQna(dto);
    }

    public void removeOneQna(int qnaNumber) {
        dao.deleteOneQna(qnaNumber);
    }
}
