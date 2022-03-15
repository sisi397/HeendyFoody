package com.heendy.dto;

/**
 * @author 김시은
 * 
 * 카테고리 정보 DTO
 * 
 * */
public class CategoryDTO {
	private int categoryId;
	private String categoryName;
	private int parentCategoryId;
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
}
