package com.heendy.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
	private String parentCategoryName;
	private List<String> categoryNames;
	
	public CategoryDTO() {
		super();
		categoryNames = new ArrayList<String>();
	}
	public String getParentCategoryName() {
		return parentCategoryName;
	}
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}
	public List<String> getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(String categoryNames) {
		this.categoryNames.add(categoryNames);
	}
}
