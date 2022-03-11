package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.heendy.utils.DBManager;
import oracle.jdbc.OracleTypes;

import com.heendy.dto.WishDTO;


public class WishDAO {
	private WishDAO() {} 
	private static WishDAO instance = new WishDAO();
	public static WishDAO getInstance() {
		return instance;
	}

	private Connection conn;
	private CallableStatement cs;
	 
	// ���ƿ��� ��ǰ ��� ��ȸ
	public ArrayList<WishDTO> listWish(int beginRow, int endRow, int member_id) {
		
		ArrayList<WishDTO> wishList = new ArrayList<WishDTO>();

		String sql =  "select S.*"
					  + " from (select rownum as rn, p.product_id, p.product_name, p.image_url, p.product_price, p.discount_price, p.product_count from member_like_product mlp, product p where mlp.product_id=p.product_id and mlp.member_id=?) S"
				 	  + " where S.rn between " + beginRow + " and " + endRow;
		  
		
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
				wishDTO.setProductPrice(rs.getInt("product_price")); //����(1�� ����)
				wishDTO.setDiscountPrice(rs.getInt("discount_price")); //���ΰ�(1�� ����) 
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
	
	
	//���ƿ��� ��ǰ �� ��
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




/**
 * @author 김시은
 * 
 * 좋아요 관련 DAO 
 * 
 * */

	  

    
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
