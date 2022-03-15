package com.heendy.dto;


/**
 * @author 김시은
 * 
 * 페이지 정보 DTO
 * 
 * */
public class PageDTO {
	private int beginPageNumber; // 시작페이지
	private int endPageNumber; // 끝 페이지
	private int pagePerList; // 페이지당 리스트 개수
	private int totalPage; // 전체 페이지 수
	
	public PageDTO(int beginPageNumber, int endPageNumber, int pagePerList, int totalPage) {
		super();
		this.beginPageNumber = beginPageNumber;
		this.endPageNumber = endPageNumber;
		this.pagePerList = pagePerList;
		this.totalPage = totalPage;
	}
	public int getBeginPageNumber() {
		return beginPageNumber;
	}
	public void setBeginPageNumber(int beginPageNumber) {
		this.beginPageNumber = beginPageNumber;
	}
	public int getEndPageNumber() {
		return endPageNumber;
	}
	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}
	public int getPagePerList() {
		return pagePerList;
	}
	public void setPagePerList(int pagePerList) {
		this.pagePerList = pagePerList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
