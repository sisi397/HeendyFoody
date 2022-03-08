package com.heendy.dto.cart;


/*
 * @author : 이승준
 * @version: 1
 * 장바구니 생성을 위한 DTO 클래스
 * */
public class CreateCartDTO {

	private int productId;
	
	private int companyId;
	
	private int memberId;
	
	private int count;

	public CreateCartDTO(int productId, int companyId, int memberId, int count) {
		this.productId = productId;
		this.companyId = companyId;
		this.memberId = memberId;
		this.count = count;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CreateCartDTO [productId=" + productId + ", companyId=" + companyId + ", memberId=" + memberId
				+ ", count=" + count + "]";
	}
	
	
	
}
