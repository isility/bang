package kr.co.jhta.bang.finalproject.util;

public class Pager {

    private int totalCount; // 페이징에 적용 할 전체 데이터 갯수
    private int pageNumber; // 현재 페이지 번호
    private int contentsNumber; // 한 페이지에 몇개 표시할지
    private int startPage=1; // 현재 페이지 블럭의 시작 페이지
    private int endPage=4; // 현제 페이지 블럭의 마지막 펭지ㅣ
    private boolean prev; // 이전 페이지로 가는 화살표
    private boolean next; // 다음 페이지로 가는 화살표
    private int currentBlock; // 현재 페이지 블럭
    private int lastBlock; // 마지막 페이지 블럭

    public void prevNext(int pageNumber){

    }

    public int calcPage(int totalCount, int contentsNumber){ // 전체 페이지 수를 계산하는 함수

        int totalPage = totalCount/contentsNumber; // totalPage -> 전체페이지 = 전체 데이터 수 / 현재 페이지 번호
        if(totalCount%contentsNumber>0){ // 나머지가 0 보다 크면 전체페이지 +1
            totalPage++;
        }
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getContentsNumber() {
        return contentsNumber;
    }

    public void setContentsNumber(int contentsNumber) {
        this.contentsNumber = contentsNumber;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int currentBlock) {
        this.startPage = (currentBlock*5)-4;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int getLastBlock, int getCurrentBlock) {
        if(getLastBlock == getCurrentBlock){
            this.endPage = calcPage(getTotalCount(), getContentsNumber()); //전체 페이지 개수가 오는 곳
        }else {
            this.endPage = getStartPage()+4;
        }
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(int pageNumber) {
        this.currentBlock = pageNumber/5;
        if(pageNumber%5>0){
            this.currentBlock++;
        }
    }

    public int getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(int lastBlock) {
        this.lastBlock = totalCount/(5*this.contentsNumber);
        if(totalCount % (5*this.contentsNumber)>0){
            this.lastBlock++;
        }
    }
}
