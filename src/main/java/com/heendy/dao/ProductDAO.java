package com.heendy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.heendy.dto.ProductDTO;

import com.heendy.utils.*;

public class ProductDAO {
	private ProductDAO() {  } //싱글턴 패턴 처리
    private static ProductDAO instance = new ProductDAO();
    public static ProductDAO getInstance() {
      return instance;
    }  
	  
	public ArrayList<ProductDTO> listProduct(int beginRow, int endRow, String sort) {	
		System.out.println(beginRow + " " + endRow);
	    ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
	    String sql = "select  rnum, product_id, company_id, product_price, product_name, image_url, product_count, product_reg_date, discount_rate, deleted, category_id, discount_price";
	    sql += " from (select row_number() over(order by "+sort+") as rnum, product_id, company_id, product_price, product_name, image_url, product_count, product_reg_date, discount_rate, deleted, category_id, discount_price from product) where rnum between "+ beginRow + " and " + endRow;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    System.out.println("DAO : listProduct");
	    try {
	    	conn = DBManager.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	rs = pstmt.executeQuery();
	    	System.out.println(sql);
	    	while (rs.next()) {
	        ProductDTO product = new ProductDTO();
	        product.setProductId(rs.getInt("product_id"));
	        product.setProductName(rs.getString("product_name"));
	        product.setProductPrice(rs.getInt("product_price"));
	        product.setImageUrl(rs.getString("image_url"));
	        productList.add(product);
	        System.out.println(product.getProductId());
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, pstmt, rs);
	    }
	    return productList;
	  }

	public ProductDTO detailProduct(String pid) {
		int productId = Integer.parseInt(pid);
		ProductDTO product = new ProductDTO();
	    String sql = "select * from product where product_id = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    System.out.println("DAO : detailProduct");
	    try {
	    	conn = DBManager.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, productId);
	    	rs = pstmt.executeQuery();
	    	System.out.println(sql);
	      while (rs.next()) {
	        product.setProductId(rs.getInt("product_id"));
	        product.setProductName(rs.getString("product_name"));
	        product.setProductPrice(rs.getInt("product_price"));
	        product.setImageUrl(rs.getString("image_url"));
	        System.out.println(product.getProductId());
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, pstmt, rs);
	    }
	    return product;
	}    
}
