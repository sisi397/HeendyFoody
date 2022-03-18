package com.heendy.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import com.heendy.utils.DBManager;
import java.sql.CallableStatement;
import java.sql.SQLException;

import java.sql.Array;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

import com.heendy.dto.OrderDTO;
import com.heendy.dto.order.CreateCartOrderDTO;
import com.heendy.dto.order.CreateOrderDTO;

/**
 * @author 이승준, 이지민
 * 
 * 주문 내역 관련 DAO 클래스
 * */
public class OrderDAO {
	private OrderDAO() {} //싱글턴 패턴 처리
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}
	
	
	/**
	 * @author : 이지민
	 * 
	 * 주문내역 조회 메서드
	 * */
	public ArrayList<OrderDTO> listOrder(int beginRow, int endRow, int member_id) throws SQLException {	
		System.out.println("member_id: " + member_id);
		System.out.println("from " + beginRow + " to " + endRow);
		
		//주문내역 담을 ArrayList
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();
		
		//DB 연결 및 callable 문장 호출
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call pkg_order.sp_list_order(?,?,?,?)}");
		
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
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setMemberId(rs.getInt("member_id"));
			orderDTO.setOrderId(rs.getInt("order_id"));
			orderDTO.setOrderTime(rs.getTimestamp("order_time"));
			orderDTO.setOrderCount(rs.getInt("order_count")); //주문 수량
			orderDTO.setOrderPrice(rs.getInt("order_price")); //할인된 가격
			orderDTO.setProductId(rs.getInt("product_id"));
			orderDTO.setProductName(rs.getString("product_name"));
			orderDTO.setImageUrl(rs.getString("image_url"));
			orderDTO.setProductPrice(rs.getInt("product_price")); //원가
			orderDTO.setCompanyId(rs.getInt("company_id"));
			orderDTO.setCompanyName(rs.getString("company_name"));
			orderList.add(orderDTO);
		}

		rs.close();
		cstmt.close();
		conn.close();	
		
		return orderList;
	}
	
	
	/**
	 * @author : 이지민
	 * 
	 * 총 주문내역 개수 조회 메서드
	 * */
	public int totalCountOrder(int member_id) throws SQLException {
		
		//변수 초기화
		int result = 0;
		
		//DB 연결 및 callable 문장 수행
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call pkg_order.sp_totalcount_order(?,?)}");
		
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
	 * @author : 이승준
	 * 
	 * 주문 내역을 생성하는 메서드
	 * */
	public void createOrder(CreateOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call pkg_order.sp_create_order(?,?,?,?)}");
		
		cstmt.setInt(1, data.getProductId());
		cstmt.setInt(2, data.getCompanyId());
		cstmt.setInt(3, data.getMemberId());
		cstmt.setInt(4, data.getCount());
		
		cstmt.execute();
		
		cstmt.close();
		conn.close();
	}
	
	/**
	 * @author : 이승준
	 * 
	 * 선택한 장바구니 목록들을 주문 내역으로 생성하는 메서드
	 * */
	public void createCartOrder(CreateCartOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		
		CallableStatement cstmt = conn.prepareCall("{ call pkg_order.sp_create_order_from_cart(?,?) }");
		
		
		Array cartIds = ((oracle.jdbc.OracleConnection)conn).createOracleArray("USER01.CARTIDSARRAY", data.getCartIds());
		
		cstmt.setInt(1, data.getMemberId());
		cstmt.setArray(2, cartIds);
		
		cstmt.execute();
		
		
		cstmt.close();
		conn.close();
	}
}
