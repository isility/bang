package kr.co.jhta.bang.finalproject.util;

import java.util.HashMap;
import java.util.Map;

// 페이징 처리 로직
public class PageUtil {
    public static Map<String, Object> getPageData(int totalNumber, int countPerPage, int currentPage){

        Map<String, Object> map = new HashMap<String, Object>();

        // 전체 페이지 수
        int totalPage = (totalNumber%countPerPage == 0 )? totalNumber/countPerPage : totalNumber/countPerPage+1;

        // 현재 페이지의 게시물 시작 번호
        int startNo = (currentPage -1)*countPerPage + 1;
        // 현재 페이지의 게시물 끝 번호
        int endNo = currentPage*currentPage;
        // 시작 페이지 번호
        int startPageNo = currentPage-5<=0?1:currentPage-5;

        // 끝 페이지 번호
        int endPageNo = currentPage+5>= totalPage?totalPage:currentPage+5;
        // 이전
        boolean prev = currentPage>5?true:false;
        // 다음
        boolean next = currentPage+5>= totalPage ? false:true;

        // map.put("키", "벨류");
        map.put("currentPage", currentPage);
        map.put("totalNumber", totalNumber);
        map.put("countPerPage", countPerPage);
        map.put("totalPage", totalPage);
        map.put("startNo", startNo);
        map.put("endNo", endNo);
        map.put("startPageNo", startPageNo);
        map.put("prev", prev);
        map.put("next", next);




            return map;
    }
}
