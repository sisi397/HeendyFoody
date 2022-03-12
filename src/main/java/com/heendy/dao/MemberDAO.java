package com.heendy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.heendy.dto.MemberDTO;
import com.heendy.utils.DBManager;

public class MemberDAO {
	private MemberDAO() { }//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}

	//회원 추가하기
	public void addMember(MemberDTO memberVO) {
		String sql = "insert into member(member_name, member_password, member_email, address, role_id, birth_date) "
				+ "values(?, pack_crypto.func_encrypt(?), ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;	    
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setString(2, memberVO.getMemberPassword());
			pstmt.setString(3, memberVO.getMemberEmail());
			pstmt.setString(4, memberVO.getAddress());
			pstmt.setInt(5, memberVO.getRoleId());
			pstmt.setString(6, memberVO.getBirthDate());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	//아이디 비밀번호가 맞는지 체크 (로그인 기능)
	public boolean isExisted(MemberDTO memberVO) {
		boolean result = false;
		String name = memberVO.getMemberName();
		String pwd = memberVO.getMemberPassword();
		Connection conn = null;
		PreparedStatement pstmt = null;	    
		try {
			conn = DBManager.getConnection();
			String sql = "select decode(count(*), 1, 'true', 'false') as result from member";
			sql += " where member_name=? and member_password=pack_crypto.func_encrypt(?)";
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
	
	//사용자 이메일과 이름으로 패스워드 찾기(비밀번호 찾기 기능)
	public String findMemberPassword(String name, String email) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			String sql = "select pack_crypto.func_decrypt(member_password) as pwd from member";
			sql += " where member_name=? and member_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("pwd");
				System.out.println("찾은 패스워드 = " + result);
			}else {
				//찾은 비밀번호가 없는경우
				result = "입력된 정보가 다르거나 가입된 정보가 없습니다.";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}
	//사용자 이메일로 사용자 이름 찾기(아이디==이름 찾기 기능)
	public String findMemberId(String email) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			String sql = "select member_name from member";
			sql += " where member_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("member_name");
				System.out.println("찾은 아이디 = " + result);
			}else {
				result = "가입한 아이디가 없습니다.";
			}


		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return result;
	}

	//아이디 중복여부체크 (1이면 사용가능, 0이면 불가능)
	public int duplicateId(String name) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			String sql = "select * from member where member_name=?";
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

	//회원 불러오기
	public MemberDTO getMember(String name) {  

		MemberDTO memberVO= null;
		String sql = "select * from member where member_name=?";	     
		Connection connn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connn = DBManager.getConnection();
			pstmt = connn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				memberVO = new MemberDTO();
				memberVO.setMemberId(rs.getInt("member_id"));
				memberVO.setMemberName(rs.getString("member_name"));
				memberVO.setMemberPassword(rs.getString("member_password"));
				memberVO.setMemberEmail(rs.getString("member_email"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setPoint(rs.getInt("point"));
				memberVO.setMemberRegDate(rs.getDate("member_reg_date"));
				memberVO.setRoleId(rs.getInt("role_id"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connn, pstmt, rs);
		}
		return memberVO;
	}

}