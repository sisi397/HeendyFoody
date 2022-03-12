package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.heendy.dto.CompanyMemberDTO;
import com.heendy.utils.DBManager;

public class CompanyMemberDAO {
	private CompanyMemberDAO() { }//싱글턴 패턴
	private static CompanyMemberDAO instance = new CompanyMemberDAO();
	public static CompanyMemberDAO getInstance() {
		return instance;
	}
	
	//업체회원 추가하기
	public void addCompanyMember(CompanyMemberDTO cmemberVO) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("call sp_add_company_member(?,?,?,?,?)");
			cstmt.setString(1, cmemberVO.getCompanyName());
			cstmt.setString(2, cmemberVO.getCompanyPassword());
			cstmt.setString(3, cmemberVO.getCompanyTel());
			cstmt.setString(4, cmemberVO.getCompanyEmail());
			cstmt.setInt(5, cmemberVO.getRole_id());
			
			cstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
	    }
	}
	//아이디 중복여부체크 (1이면 사용가능, 0이면 불가능)
	public int duplicateCompanyId(String name) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			String sql = "select * from company_member where company_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
}
