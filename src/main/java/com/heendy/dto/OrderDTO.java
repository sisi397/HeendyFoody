package com.heendy.dto;
import java.sql.Timestamp;

public class OrderDTO {

	  private int orderId;
	  private int memberId;
	  private Timestamp orderTime;
	  private int orderCount;
	  private int orderPrice;
	  private int productId;
	  private int companyId;
	  private String productName;
	  private String imageUrl;
	  private int productPrice;
	  private String companyName;
	  
	  
	  public int getOrderId() {
		  return orderId;  
	  }
	  
	  public void setOrderId(int orderId) {
		  this.orderId = orderId;  
	  }
	  
	  public int getMemberId() {
		  return memberId;  
	  }
	  
	  public void setMemberId(int memberId) {
		  this.memberId = memberId;
	  }
	  
	  public Timestamp getOrderTime() {
		  return orderTime;  
	  }
	  
	  public void setOrderTime(Timestamp orderTime) {
		  this.orderTime = orderTime;
	  }
	  
	  public int getorderCount() {
		  return orderCount;  
	  }
	  
	  public void setOrderCount(int orderCount) {
		  this.orderCount = orderCount;
	  }
	  
	  public int getOrderPrice() {
		  return orderPrice;
	  }
	  
	  public void setOrderPrice(int orderPrice) {
		  this.orderPrice = orderPrice;
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

	  public String getProductName() {
		  return productName;
	  }

	  public void setProductName(String productName) {
		  this.productName = productName;
	  }
	
	  public String getImageUrl() {
		  return imageUrl;
	  }

	  public void setImageUrl(String imageUrl) {
		  this.imageUrl = imageUrl;
	  }

	  public String getCompanyName() {
		  return companyName;
	  }

	  public void setCompanyName(String companyName) {
		  this.companyName = companyName;
	  }

	  public int getProductPrice() {
		  return productPrice;
	  }

	  public void setProductPrice(int productPrice) {
		  this.productPrice = productPrice;
	  }

}
