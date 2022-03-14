package com.heendy.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.heendy.utils.DBManager;
//import com.heendy.dto.CartVO;
import com.heendy.dto.OrderDTO;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.SQLException;
import com.heendy.dto.order.CreateCartOrderDTO;
import com.heendy.dto.order.CreateOrderDTO;


public class OrderDAO {
	private OrderDAO() {} 
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}
	
	//�ֹ� ���� ��ȸ
	public ArrayList<OrderDTO> listOrder(int beginRow, int endRow, int member_id) {	
		System.out.println("member_id: " + member_id);
		System.out.println("from " + beginRow + " to " + endRow);
		
		ArrayList<OrderDTO> orderList = new ArrayList<OrderDTO>();

		String sql =  "select S.*"
					  + " from (select rownum as rn, p.product_name, p.image_url, p.product_price, po.*, cm.company_name from product p, product_order po, company_member cm where po.product_id=p.product_id and po.company_id=cm.company_id and po.member_id=? order by po.order_time desc) S"
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
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setOrderId(rs.getInt("order_id"));
				orderDTO.setMemberId(rs.getInt("member_id"));
				orderDTO.setOrderTime(rs.getTimestamp("order_time"));
				orderDTO.setOrderCount(rs.getInt("order_count"));
				orderDTO.setOrderPrice(rs.getInt("order_price")); //���ΰ�(1�� ����) 
				orderDTO.setProductId(rs.getInt("product_id"));
				orderDTO.setCompanyId(rs.getInt("company_id"));
				orderDTO.setProductName(rs.getString("product_name"));
				orderDTO.setImageUrl(rs.getString("image_url"));
				orderDTO.setProductPrice(rs.getInt("product_price")); //����(1�� ����)
				orderDTO.setCompanyName(rs.getString("company_name"));
				orderList.add(orderDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return orderList;
	}
	
	public int totalCountOrder(int member_id) {
		
		int result = 0;
		String sql = "select count(*) from product_order where member_id=?";
		
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
 * @author : 이승준
 * @version : 1
 * 결제 DAO 클래스
 * */

	
	
	public void createOrder(CreateOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call sp_create_order(?,?,?,?)}");
		
		cstmt.setInt(1, data.getProductId());
		cstmt.setInt(2, data.getCompanyId());
		cstmt.setInt(3, data.getMemberId());
		cstmt.setInt(4, data.getCount());
		
		cstmt.execute();
		
		cstmt.close();
		conn.close();
	}
	
	public void createCartOrder(CreateCartOrderDTO data) throws SQLException {
		Connection conn = DBManager.getConnection();
		
		
		CallableStatement cstmt = conn.prepareCall("{ call sp_create_order_from_cart(?,?) }");
		
		
		Array cartIds = ((oracle.jdbc.OracleConnection)conn).createOracleArray("USER01.CARTIDSARRAY", data.getCartIds());
		
		cstmt.setInt(1, data.getMemberId());
		cstmt.setArray(2, cartIds);
		
		cstmt.execute();
		
		
		cstmt.close();
		conn.close();
	}
}
