package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

/**
 * @author 김시은
 * 
 * 좋아요 관련 DAO 
 * 
 * */
public class WishDAO {
	private WishDAO() {  }
    private static WishDAO instance = new WishDAO();
    public static WishDAO getInstance() {
      return instance;
    }  
	  
    private Connection conn;
    
    private CallableStatement cs;
    
    // 좋아요 추가
	public int insertWish(int memberId, int productId, int companyId) {
	    int result = 0;	
	    String sql = "{CALL sp_insert_wish(?, ?, ?)}";
	    		
	    conn = null;
	    cs = null;
	    System.out.println("DAO : insertWish");
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    cs.setInt(1, memberId);
		    cs.setInt(2, productId);
		    cs.setInt(3, companyId);
		    result = cs.executeUpdate();
		    System.out.println(result);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return result;
	}
	
	// 좋아요 삭제
	public int deleteWish(int memberId, int productId, int companyId) {
	    int result = 0;	
	    String sql = "{CALL sp_delete_wish(?,?,?)}";
	    
	    System.out.println("DAO : deleteWish");
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
		    cs.setInt(1, memberId);
		    cs.setInt(2, productId);
		    cs.setInt(3, companyId);
		    result = cs.executeUpdate();
		    System.out.println(result);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return result;
	}
	
	// 좋아요 여부 check
	public int wishCheck(int memberId, int productId, int companyId) {
		int result = 0;	
	    String sql = "{CALL sp_check_wish(?,?,?,?)}";
	    
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);
	    	cs.setInt(1, memberId);
	    	cs.setInt(2, productId);
	    	cs.setInt(3, companyId);
		    cs.registerOutParameter(4, OracleTypes.INTEGER);
	    	cs.executeUpdate();
	    	result = cs.getInt(4);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
		return result;
	}
}
