package com.heendy.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.heendy.utils.DBManager;
//import com.heendy.dto.CartVO;
import com.heendy.dto.OrderDTO;

public class OrderDAO {
	private OrderDAO() {} 
	private static OrderDAO instance = new OrderDAO();
	public static OrderDAO getInstance() {
		return instance;
	}
	
	//주문 내역 조회
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
				orderDTO.setOrderPrice(rs.getInt("order_price")); //할인가(1개 기준) 
				orderDTO.setProductId(rs.getInt("product_id"));
				orderDTO.setCompanyId(rs.getInt("company_id"));
				orderDTO.setProductName(rs.getString("product_name"));
				orderDTO.setImageUrl(rs.getString("image_url"));
				orderDTO.setProductPrice(rs.getInt("product_price")); //원가(1개 기준)
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
	
	public int totalCountOrder() {
		
		int result = 0;
		String sql = "select count(*) from product_order";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			System.out.println("***************************DB*********************************");
			pstmt = conn.prepareStatement(sql);	
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
	
}//end class