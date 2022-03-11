package com.heendy.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.heendy.utils.DBManager;

import com.heendy.dto.WishDTO;


public class WishDAO {
	private WishDAO() {} 
	private static WishDAO instance = new WishDAO();
	public static WishDAO getInstance() {
		return instance;
	}
	 
	// ÁÁ¾Æ¿äÇÑ »óÇ° ¸ñ·Ï Á¶È¸
	public ArrayList<WishDTO> listWish(int beginRow, int endRow, int member_id) {
		
		ArrayList<WishDTO> wishList = new ArrayList<WishDTO>();

		String sql =  "select S.*"
					  + " from (select rownum as rn, p.product_id, p.product_name, p.image_url, p.product_price, p.discount_price, p.product_count from member_like_product mlp, product p where mlp.product_id=p.product_id and mlp.member_id=?) S"
				 	  + " where S.rn between " + beginRow + " and " + endRow;
		  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = DBManager.getConnection();
			System.out.println("***************************DB*********************************");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_id);		
			rs = pstmt.executeQuery();			
			
			while (rs.next()) {
				WishDTO wishDTO = new WishDTO();
				wishDTO.setMemberId(member_id);
				wishDTO.setProductId(rs.getInt("product_id"));
				wishDTO.setProductName(rs.getString("product_name"));
				wishDTO.setImageUrl(rs.getString("image_url"));
				wishDTO.setProductPrice(rs.getInt("product_price")); //ï¿½ï¿½ï¿½ï¿½(1ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½)
				wishDTO.setDiscountPrice(rs.getInt("discount_price")); //ï¿½ï¿½ï¿½Î°ï¿½(1ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½) 
				wishDTO.setProductCount(rs.getInt("product_count"));
				wishList.add(wishDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return wishList;
	}
	
	
	//ÁÁ¾Æ¿äÇÑ »óÇ° ÃÑ ¼ö
	public int totalWishCount(int member_id) {
		
		int result = 0;
		String sql = "select count(*) from member_like_product where member_id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("***************************DB*********************************");
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, member_id);		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;	
		}


	  
    // ÁÁ¾Æ¿ä insert
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
	
	// ÁÁ¾Æ¿ä delete
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
	
	// ÁÁ¾Æ¿ä ¿©ºÎ
	public int wishIs(int memberId, int productId) {
		int result = 0;	
	    String sql = "select count(*) from member_like_product where member_id = ? and product_id = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    try {
	    	conn = DBManager.getConnection();
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, memberId);
	    	pstmt.setInt(2, productId);
	    	rs = pstmt.executeQuery();
	    	System.out.println(sql);
	      while (rs.next()) {
	    	  result = rs.getInt(1);
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, pstmt, rs);
	    }
		return result;
	}
}
