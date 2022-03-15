package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import com.heendy.dto.ProductDTO;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

/**
 * @author 김시은
 * 
 * 업체 페이지의 Chart 데이터 관련 DAO 
 * 
 * */
public class ChartDataDAO {
	private ChartDataDAO() {  } //싱글턴 패턴 처리
    private static ChartDataDAO instance = new ChartDataDAO();
    public static ChartDataDAO getInstance() {
      return instance;
    }  
    
    private Connection conn;
    
    private CallableStatement cs;
    
    private ResultSet rs;
    
    // 구매자 연령층 data 가져오기
	public List<JSONObject> ageInfo(int cid) throws SQLException{
		List<JSONObject> memberList = new LinkedList<JSONObject>();
		
		String sql = "{CALL pkg_company.SP_MEMBER_AGEINFO(?,?)}";
	    
    	conn = DBManager.getConnection();
    	cs = conn.prepareCall(sql);

	    cs.setInt(1, cid);
	    cs.registerOutParameter(2, OracleTypes.CURSOR); // 구매자 연령층 데이터 (연령대, 구매자수)
	    
	    cs.executeUpdate();
	    
	    rs = (ResultSet)cs.getObject(2);
	    
	    JSONObject memberObj = null;
        while (rs.next()) {
        	String group = rs.getString("sorting");
    	    int count = rs.getInt("count");
    	    memberObj = new JSONObject();
    	    memberObj.put("group", group);
    	    memberObj.put("count", count);
    	    memberList.add(memberObj);
        }
		    
	    DBManager.close(conn, cs);
	    
	    return memberList;
	}
	
	// 날짜별 구매량 data 가져오기
	public List<JSONObject> orderInfo(int cid, String sort, int pid) throws SQLException {
		List<JSONObject> orderList = new LinkedList<JSONObject>();
		
		String sql = "{CALL pkg_company.SP_PRODUCT_ORDERINFO(?,?,?,?)}";
	    
    	conn = DBManager.getConnection();
    	cs = conn.prepareCall(sql);

	    cs.setInt(1, cid);
	    cs.setInt(2, pid);
	    cs.setString(3, sort);
	    cs.registerOutParameter(4, OracleTypes.CURSOR); // 날짜별 구매량 데이터 (날짜, 구매량)
	    
	    cs.executeUpdate();
	    
	    rs = (ResultSet)cs.getObject(4);
	    
	    JSONObject orderObj = null;
        while (rs.next()) {
        	String group = rs.getString("sorting");
    	    int count = rs.getInt("count");
    	    orderObj = new JSONObject();
    	    orderObj.put("group", group);
    	    orderObj.put("count", count);
    	    orderList.add(orderObj);
        }
		    
	    DBManager.close(conn, cs);
	    
	    return orderList;
	}

	// 업체별 상품 리스트 불러오기
	public List<ProductDTO> productList(int cid) throws SQLException{
		ArrayList<ProductDTO> productList = new ArrayList<ProductDTO>();
		    
		String sql = "{CALL pkg_product.SP_COMPANY_PRODUCT(?,?)}";
		
    	conn = DBManager.getConnection();
    	cs = conn.prepareCall(sql);

	    cs.setInt(1, cid);
	    cs.registerOutParameter(2, OracleTypes.CURSOR); //업체별 상품 리스트
	    
	    cs.executeUpdate();
	    
	    rs = (ResultSet)cs.getObject(2);
	    
        while (rs.next()) {
        	ProductDTO product = new ProductDTO();
	        product.setProductId(rs.getInt("product_id"));
	        product.setCompanyId(rs.getInt("company_id"));
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
	        productList.add(product);
	        System.out.println(product.getProductId());
        }
		 
	        DBManager.close(conn, cs);
	    	
	    return productList;
	}
}
