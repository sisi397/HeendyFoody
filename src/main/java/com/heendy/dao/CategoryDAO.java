package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.heendy.dto.CategoryDTO;
import com.heendy.dto.ProductDTO;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

/**
 * @author 김시은
 * 
 * 카테고리 관련 DAO 
 * 
 * */
public class CategoryDAO {
	
	private CategoryDAO() {  } //싱글턴 패턴 처리
    private static CategoryDAO instance = new CategoryDAO();
    public static CategoryDAO getInstance() {
      return instance;
    }  
    
    // 오라클 연결
    private Connection conn;
    
    // sql문장전송, 함수 호출
    private CallableStatement cs;
    
    private ResultSet rs;
    
    // 카테고리 정보 가져오기
	public ArrayList<CategoryDTO> listCategory(int cate, int pcate) {
		ArrayList<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
		
		String sql = "{CALL sp_list_category(?,?,?,?)}";
	    System.out.println("p : " + pcate);
	    
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);

		    cs.setInt(1, cate);
		    cs.setInt(2, pcate);
		    cs.registerOutParameter(3, OracleTypes.VARCHAR);
		    cs.registerOutParameter(4, OracleTypes.CURSOR);
		    
		    cs.executeUpdate();
		    String categoryName = cs.getString(3);
		    CategoryDTO category = new CategoryDTO();
		    category.setCategoryName(categoryName);
		    categoryList.add(category);
		    
		    rs = (ResultSet)cs.getObject(4);
		    
	        while (rs.next()) {
	    	   category = new CategoryDTO();
	    	   category.setCategoryId(rs.getInt("category_id"));
	    	   category.setCategoryName(rs.getString("category_name"));
	    	   category.setParentCategoryId(rs.getInt("parent_category_id"));
	    	   categoryList.add(category);
	        }
		    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return categoryList;
	}
}
