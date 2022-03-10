package com.heendy.dto;

public class PageDTO {
	private int beginPageNumber;
	private int endPageNumber;
	private int pagePerList;
	private int totalPage;
	
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
