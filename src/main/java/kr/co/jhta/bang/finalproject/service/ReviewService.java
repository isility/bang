package kr.co.jhta.bang.finalproject.service;

import kr.co.jhta.bang.finalproject.dao.ReviewDAO;
import kr.co.jhta.bang.finalproject.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewDAO dao;
    public List<ReviewDTO> getAll() {

        return dao.getAll();
    }
//    public List<ReviewDTO> findAllReply(int startNo, int endNo) {
//        StartEnd se = new StartEnd(startNo, endNo);
//        return dao.findAllReply(se);
//    }

    public ReviewDTO findByReply_number(int replyNumber) {
        return dao.findByReply_number(replyNumber);
    }

    public int getTotal(){
        return dao.getTotal();
    }



//    @Override
//    public int execute(Model model, String pageNumber, String contentsNumber) {
//        Pager pageMaker = new Pager();
//
//        int cpageNumber = Integer.parseInt(pageNumber);
//        int ccontentsNumber = Integer.parseInt(contentsNumber);
//
//        List<ReviewDTO> list = null;
//
//        pageMaker.setTotalCount(dao.reviewCount()); // mapper 전체 게시글 개수를 지정
//        pageMaker.setPageNumber(cpageNumber-1); // 현재 페이지를 페이지 객체에 지정, -1을 해야 쿼리에서 사용할 수 있음
//        pageMaker.setContentsNumber(ccontentsNumber); // 한 페이지에 몇개 씩 게시그를 보여줄지 지정
//        pageMaker.setCurrentBlock(cpageNumber); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정
//        pageMaker.setLastBlock(pageMaker.getTotalCount()); // 마지막 블록 번호를 전체 게시글 수를 통해서 정함
//
//        pageMaker.prevNext(cpageNumber); // 현재 페이지 번호를 화살표를 나타낼지 정함
//        pageMaker.setStartPage(pageMaker.getCurrentBlock()); // 시작 페이지를 페이지 블록번호로 정함
//        pageMaker.setEndPage(pageMaker.getLastBlock(), pageMaker.getCurrentBlock()); // 마지막 페이지를 마지막 페이지 블록과 현제 페이지 블록 번호로 정함
//
//        if(ccontentsNumber == 10){ // 선택 게시글 수
//            list=dao.list(pageMaker.getPageNumber()*10, pageMaker.getContentsNumber());
//        }else if(ccontentsNumber == 20){
//            list=dao.list(pageMaker.getPageNumber()*20, pageMaker.getContentsNumber());
//        }else if(ccontentsNumber == 30){
//            list=dao.list(pageMaker.getPageNumber()*30, pageMaker.getContentsNumber());
//        }
//
//        model.addAttribute("list", list);
//        model.addAttribute("page", pageMaker);
//         return 0;
//    }
//
//    public int execute(Model model, String pageNumber, String contentsNumber);
}
