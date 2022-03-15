package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.heendy.dto.CompanyMemberDTO;
import com.heendy.dto.MemberDTO;
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
	
	//업체 아이디 비밀번호가 맞는지 체크 (로그인 기능)
	public boolean isExisted(CompanyMemberDTO cmemberVO) {
		boolean result = false;
		String name = cmemberVO.getCompanyName();
		String pwd = cmemberVO.getCompanyPassword();
		Connection conn = null;
		PreparedStatement pstmt = null;	    
		try {
			conn = DBManager.getConnection();
			String sql = "select decode(count(*), 1, 'true', 'false') as result from company_member";
			sql += " where company_name=? and company_password=pack_crypto.func_encrypt(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result = " + result);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	public CompanyMemberDTO getCompanyMember(String company_name) {
		CompanyMemberDTO cmemberVO = null;
		String sql = "select * from company_member where company_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cmemberVO = new CompanyMemberDTO();
				cmemberVO.setCompanyId(rs.getInt("company_id"));
				cmemberVO.setCompanyName(rs.getString("company_name"));
				cmemberVO.setCompanyPassword(rs.getString("company_password"));
				cmemberVO.setCompanyTel(rs.getString("company_tel"));
				cmemberVO.setCompanyEmail(rs.getString("company_email"));
				cmemberVO.setRole_id(rs.getInt("role_id"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return cmemberVO;
	}
}
