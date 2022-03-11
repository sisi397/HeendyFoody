package com.heendy.dao;

import java.sql.Connection;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.heendy.dto.RecentViewDTO;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

public class RecentViewDAO {
	private RecentViewDAO() {} 
	private static RecentViewDAO instance = new RecentViewDAO();
	public static RecentViewDAO getInstance() {
		return instance;
	}
	
	private Connection conn;
	private CallableStatement cstmt;
	private ResultSet rs;
	
	//최근 본 상품목록 조회
	public ArrayList<RecentViewDTO> listRecentView(Integer[] rvProducts) {	
		System.out.println("rv_list: " + rvProducts);
		
		ArrayList<RecentViewDTO> recentViewList = new ArrayList<RecentViewDTO>();
		
					
		try {
			conn = DBManager.getConnection();
			System.out.println("***************************DB*********************************");
			
			cstmt = conn.prepareCall("{ call sp_list_recent_view_product(?, ?) }");
			Array rvItems = ((oracle.jdbc.OracleConnection)conn).createOracleArray("USER01.RECENTVIEWPRODUCTIDSARRAY", rvProducts);
			cstmt.setArray(1, rvItems);	
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			
			rs = (ResultSet)cstmt.getObject(2);
			
			while (rs.next()) {
				RecentViewDTO recentViewDTO = new RecentViewDTO();
				recentViewDTO.setProductId(rs.getInt("product_id"));
				recentViewDTO.setProductName(rs.getString("product_name"));
				recentViewDTO.setImageUrl(rs.getString("image_url"));
				recentViewDTO.setProductCount(rs.getInt("product_count"));
				recentViewDTO.setProductPrice(rs.getInt("product_price")); //원가(1개 기준)
				recentViewDTO.setDiscountPrice(rs.getInt("discount_price")); //할인가(1개 기준) 
				recentViewList.add(recentViewDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, cstmt, rs);
		}
		return recentViewList;
	}
}
