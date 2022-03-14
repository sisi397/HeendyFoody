package com.heendy.dto;

public class CreateProductDTO {
	
	
	private int companyId;
	
	private String productName;
	
	private int price;
	
	private int dicountRate;
	
	private int count;
	
	private String imageUrl;
	
	private int categoryId;
	
	

	
	public CreateProductDTO(int companyId, String productName, int price, int dicountRate, int count, String imageUrl,
			int categoryId) {
		this.companyId = companyId;
		this.productName = productName;
		this.price = price;
		this.dicountRate = dicountRate;
		this.count = count;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDicountRate() {
		return dicountRate;
	}

	public void setDicountRate(int dicountRate) {
		this.dicountRate = dicountRate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "CreateProductDTO [companyId=" + companyId + ", productName=" + productName + ", price=" + price
				+ ", dicountRate=" + dicountRate + ", count=" + count + ", imageName=" + imageUrl + ", categoryId="
				+ categoryId + "]";
	}
	
	
	

}
