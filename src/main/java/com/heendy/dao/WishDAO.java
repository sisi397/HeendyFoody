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
	
	// 좋아요 조회
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
				wishDTO.setProductPrice(rs.getInt("product_price")); //원가(1개 기준)
				wishDTO.setDiscountPrice(rs.getInt("discount_price")); //할인가(1개 기준) 
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
	
	
	//좋아요한 상품 총 수
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
}
