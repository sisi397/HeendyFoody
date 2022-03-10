package com.heendy.dto.order;

/**
 * @author 이승준
 * 
 * 주문 생성 DTO 클래스	
 * */
public class CreateOrderDTO {

	private int productId;
	
	private int companyId;
	
	private int memberId;
	
	private int count;

	public CreateOrderDTO(int productId, int companyId, int memberId, int count) {
		super();
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
		return "CreateOrderDTO [productId=" + productId + ", companyId=" + companyId + ", memberId=" + memberId
				+ ", count=" + count + "]";
	}
	
	
}
