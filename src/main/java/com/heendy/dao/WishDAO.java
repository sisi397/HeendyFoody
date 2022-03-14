package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.heendy.utils.DBManager;
import oracle.jdbc.OracleTypes;

import com.heendy.dto.WishDTO;


public class WishDAO {
	private WishDAO() {} //싱글턴 패턴 처리
	private static WishDAO instance = new WishDAO();
	public static WishDAO getInstance() {
		return instance;
	}

	private Connection conn;
	private CallableStatement cs;
	 
	/**
	 * @author : 이지민
	 * 좋아요 목록 DAO 클래스
	 * */
	
	// 좋아요 목록 조회 메서드
	public ArrayList<WishDTO> listWish(int beginRow, int endRow, int member_id) throws SQLException {
		System.out.println(beginRow + " to " + endRow);
		
		//좋아요 목록 담을 ArrayList
		ArrayList<WishDTO> wishList = new ArrayList<WishDTO>();

		//DB 연결 및 callable 문장 호출
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call sp_list_wish(?,?,?,?)}");
		
		//?에 인자 넘기기
		cstmt.setInt(1, beginRow);
		cstmt.setInt(2, endRow);
		cstmt.setInt(3, member_id);
		cstmt.registerOutParameter(4, OracleTypes.CURSOR); //return 받을 위치와 타입 설정
		
		//수행
		cstmt.executeQuery();

		//return 값 받기
		ResultSet rs =  (ResultSet) cstmt.getObject(4);
		  		
		while (rs.next()) {
			WishDTO wishDTO = new WishDTO();
			wishDTO.setMemberId(member_id);
			wishDTO.setCompanyId(rs.getInt("company_id"));
			wishDTO.setProductId(rs.getInt("product_id"));
			wishDTO.setProductName(rs.getString("product_name"));
			wishDTO.setImageUrl(rs.getString("image_url"));
			wishDTO.setProductPrice(rs.getInt("product_price")); //원가
			wishDTO.setDiscountPrice(rs.getInt("discount_price")); //할인가 
			wishDTO.setProductCount(rs.getInt("product_count"));
			wishDTO.setDeleted(rs.getInt("deleted"));
			wishList.add(wishDTO);
		}
		
		rs.close();
		cstmt.close();
		conn.close();	
		
		return wishList;
	}
	
	
	//총 좋아요 개수 조회 메서드
	public int totalWishCount(int member_id) throws SQLException {
		
		//변수 초기화
		int result = 0;
		
		//DB 연결 및 callable 문장 수행
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call sp_totalcount_wish(?,?)}");
		
		//?에 인자 넘기기
		cstmt.setInt(1, member_id);
		cstmt.registerOutParameter(2, OracleTypes.INTEGER); //return 받을 위치와 타입 설정
		
		//수행
		cstmt.executeQuery();

		//return 값 받기
		result = cstmt.getInt(2);
				
		cstmt.close();
		conn.close();
		
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
