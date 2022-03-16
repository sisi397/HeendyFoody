package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.heendy.dto.CompanyMemberDTO;
import com.heendy.dto.MemberDTO;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;
/**
 * @author 문석호
 * 업체 멤버 관련 DAO 클래스
 */
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
	//아이디 중복여부체크 (1이면 사용불가 0이면 사용가능)
	public int duplicateCompanyId(String name) {
		int result = -1;
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_company_duplicatedId(?,?)}");
			cstmt.setString(1, name);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			//실행
			cstmt.executeQuery();
			//결과 받아오기
			result = cstmt.getInt(2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}
	
	//업체 아이디 비밀번호가 맞는지 체크 (로그인 기능)
	public int isExisted(CompanyMemberDTO cmemberVO) {
		int result = -1;
		String name = cmemberVO.getCompanyName();
		String pwd = cmemberVO.getCompanyPassword();
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_company_isExisted(?,?,?)}");
			cstmt.setString(1, name);
			cstmt.setString(2, pwd);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);
			cstmt.executeQuery();
			
			//결과 받아오기(1이면 true, 0이면 false)
			result = cstmt.getInt(3);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}

	public CompanyMemberDTO getCompanyMember(String company_name) {
		CompanyMemberDTO cmemberVO = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_company_getcompany(?,?)}");
			cstmt.setString(1, company_name);
			cstmt.registerOutParameter(2,OracleTypes.CURSOR);
			//실행
			cstmt.executeQuery();
			//커서 객체 받아오기
			rs = (ResultSet)cstmt.getObject(2);
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
			DBManager.close(conn, cstmt);
		}
		return cmemberVO;
	}
}
