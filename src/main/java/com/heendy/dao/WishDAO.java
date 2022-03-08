package com.heendy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.heendy.utils.DBManager;

public class WishDAO {
	private WishDAO() {  } //싱글턴 패턴 처리
    private static WishDAO instance = new WishDAO();
    public static WishDAO getInstance() {
      return instance;
    }  
	  
    // 좋아요 insert
	public int insertWish(int memberId, int productId, int companyId) {
	    int result = 0;	
	    String sql = "insert into member_like_product(member_id, product_id, company_id) "
	    		+ "values(?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    System.out.println("DAO : insertWish");
	    try {
	    	conn = DBManager.getConnection();
	    	pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, memberId);
		    pstmt.setInt(2, productId);
		    pstmt.setInt(3, companyId);
		    result = pstmt.executeUpdate();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, pstmt);
	    }
	    return result;
	}
	
	// 좋아요 delete
	public int deleteWish(int memberId, int productId) {
	    int result = 0;	
	    String sql = "delete from member_like_product where member_id = ? and product_id = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    System.out.println("DAO : insertWish");
	    try {
	    	conn = DBManager.getConnection();
	    	pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, memberId);
		    pstmt.setInt(2, productId);
		    result = pstmt.executeUpdate();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, pstmt);
	    }
	    return result;
	}
}
