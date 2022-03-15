package com.heendy.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.heendy.utils.DBManager;

import java.sql.Array;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

import com.heendy.dto.RecentViewDTO;


/**
 * @author : 이지민
 * 최근 본 상품 목록 DAO 클래스
 * */

public class RecentViewDAO {
	private RecentViewDAO() {} //싱글턴 패턴 처리
	private static RecentViewDAO instance = new RecentViewDAO();
	public static RecentViewDAO getInstance() {
		return instance;
	}
	
	
	//최근 본 상품 목록 조회 메서드
	public ArrayList<RecentViewDTO> listRecentView(Integer[] rvProducts) throws SQLException {	
		System.out.println("rv_list: " + rvProducts);
		
		//최근 본 상품 목록 담을 ArrayList
		ArrayList<RecentViewDTO> recentViewList = new ArrayList<RecentViewDTO>();
		
		//DB 연결 및 callable 문장 호출
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call sp_list_recent_view_product(?, ?)}");
					
		//Integer[] 타입의 인자를 DB에서 커스텀해서 만든 array 타입으로 변환 필요   
		Array rvItems = ((oracle.jdbc.OracleConnection)conn).createOracleArray("USER01.RECENTVIEWPRODUCTIDSARRAY", rvProducts);
		
		//?에 인자 넘기기
		cstmt.setArray(1, rvItems);	
		cstmt.registerOutParameter(2, OracleTypes.CURSOR); //return 받을 위치와 타입 설정
		
		//수행
		cstmt.executeQuery();
		
		//return 값 받기
		ResultSet rs = (ResultSet) cstmt.getObject(2);
		
		while (rs.next()) {
			RecentViewDTO recentViewDTO = new RecentViewDTO();
			recentViewDTO.setProductId(rs.getInt("product_id"));
			recentViewDTO.setCompanyId(rs.getInt("company_id"));
			recentViewDTO.setProductName(rs.getString("product_name"));
			recentViewDTO.setImageUrl(rs.getString("image_url"));
			recentViewDTO.setProductCount(rs.getInt("product_count"));
			recentViewDTO.setProductPrice(rs.getInt("product_price")); //원가
			recentViewDTO.setDiscountPrice(rs.getInt("discount_price")); //할인가 
			recentViewDTO.setDeleted(rs.getInt("deleted"));
			recentViewList.add(recentViewDTO);
		}
		
		rs.close();
		cstmt.close();
		conn.close();
		
		return recentViewList;
	}
}
