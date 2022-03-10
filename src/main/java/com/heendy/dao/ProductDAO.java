package com.heendy.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.heendy.dto.CategoryDTO;
import com.heendy.dto.ProductDTO;

import com.heendy.utils.*;

import oracle.jdbc.OracleTypes;

public class ProductDAO {
	private ProductDAO() {  } //싱글턴 패턴 처리
    private static ProductDAO instance = new ProductDAO();
    public static ProductDAO getInstance() {
      return instance;
    }  
    
    // 오라클 연결
    private Connection conn;
    
    // sql문장전송, 함수 호출
    private CallableStatement cs;
    
    private ResultSet rs;
	  
	public ArrayList<ProductDTO> listProduct(int beginRow, int endRow, String sort, String menu, int cate, int pcate) {
	    ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
	    String sql = "{CALL sp_list_product(?,?,?,?,?,?,?)}";
	    System.out.println("DAO : listProduct");
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    
		    //?에 값 채우기
		    cs.setInt(1, beginRow);
		    cs.setInt(2, endRow);
		    cs.setString(3, sort);
		    cs.setString(4, menu);
		    cs.setInt(5, cate);
		    cs.setInt(6, pcate);
		    cs.registerOutParameter(7, OracleTypes.CURSOR);
		    System.out.println(sort);
		    /*
		     * oracle 데이터형 설정
		     * sys_refcursor => oracleTypes.cursor
		     * varchar2 => oracleTypes.varchar
		     */
		    cs.executeUpdate();
		    rs = (ResultSet)cs.getObject(7);
		    
	    	while (rs.next()) {
		        ProductDTO product = new ProductDTO();
		        product.setProductId(rs.getInt("product_id"));
		        product.setCompanyName(rs.getString("company_name"));
		        product.setProductPrice(rs.getInt("product_price"));
		        product.setProductName(rs.getString("product_name"));
		        product.setImageUrl(rs.getString("image_url"));
		        product.setProductCount(rs.getInt("product_count"));
		        product.setProductRegDate(rs.getString("product_reg_date"));
		        product.setDiscountRate(rs.getInt("discount_rate"));
		        product.setDeleted(rs.getInt("deleted"));
		        product.setCategoryId(rs.getInt("category_id"));
		        product.setDiscountPrice(rs.getInt("discount_price"));
		        System.out.println(product.getProductId());
		        productList.add(product);
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs, rs);
	    }
	    return productList;
	  }

	public ProductDTO detailProduct(int pid){
		ProductDTO product = new ProductDTO();
		
	    String sql = "{CALL sp_select_product(?,?)}";
	    
	    conn = null;
	    cs = null;
	    rs = null;    
	    System.out.println("DAO : detailProduct");
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    
		    //?에 값 채우기
		    cs.setInt(1, pid);
		    cs.registerOutParameter(2, OracleTypes.CURSOR);
		    /*
		     * oracle 데이터형 설정
		     * sys_refcursor => oracleTypes.cursor
		     * varchar2 => oracleTypes.varchar
		     */
		    cs.executeUpdate();
		    rs = (ResultSet)cs.getObject(2);
	      while (rs.next()) {
	        product.setProductId(rs.getInt("product_id"));
	        product.setCompanyName(rs.getString("company_Name"));
	        product.setCompanyId(rs.getInt("company_id"));
	        product.setProductPrice(rs.getInt("product_price"));
	        product.setProductName(rs.getString("product_name"));
	        product.setImageUrl(rs.getString("image_url"));
	        product.setProductCount(rs.getInt("product_count"));
	        product.setProductRegDate(rs.getString("product_reg_date"));
	        product.setDiscountRate(rs.getInt("discount_rate"));
	        product.setDeleted(rs.getInt("deleted"));
	        product.setCategoryId(rs.getInt("category_id"));
	        product.setPcategoryId(rs.getInt("parent_category_id"));
	        product.setDiscountPrice(rs.getInt("discount_price"));
	        product.setParentCategoryName(rs.getString("parent_category_name"));
	        product.setCategoryName(rs.getString("category_name"));
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs, rs);
	    }
	    return product;
	}    

	public int totalCountProduct(String menu) {
		int result = 0;
	    String sql = "{CALL sp_totalcount_product(?,?)}";

	    conn = null;
	    cs = null;
	    System.out.println("DAO : totalCountProduct");
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    
		    //?에 값 채우기
		    cs.setString(1, menu);
		    cs.registerOutParameter(2, OracleTypes.INTEGER);
		    
		    cs.executeUpdate();
		    result = cs.getInt(2);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return result;
	}
	
	public CategoryDTO listCategory(int pcate) {
		CategoryDTO category = new CategoryDTO();
		String sql = "{CALL sp_list_category(?,?,?)}";
	    System.out.println("p : " + pcate);

	    conn = null;
	    cs = null;
	    
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    
		    //?에 값 채우기
		    cs.setInt(1, pcate);
		    cs.registerOutParameter(2, OracleTypes.VARCHAR);
		    cs.registerOutParameter(3, OracleTypes.CURSOR);
		    
		    cs.executeUpdate();
		    category.setParentCategoryName(cs.getString(2));

		    rs = (ResultSet)cs.getObject(3);
	      while (rs.next()) {
	        category.setCategoryNames(rs.getString(1));
	      }
		    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return category;
	}
}
