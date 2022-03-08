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

	//회원 목록 조회
	public List<MemberDTO> listMembers() {
		List<MemberDTO> membersList = new ArrayList();
		
		return membersList;
	}

	//회원 추가하기
	public void addMember(MemberDTO memberVO) {
		int result = 0;
		String sql = "insert into member(member_name, member_password, member_email, address, role_id) "
	    		+ "values(?, ?, ?, ?, ?) ";
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
		    pstmt.executeUpdate();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBManager.close(conn, pstmt);
	    }
	}
	//로그인 기능
	public boolean isExisted(MemberDTO memberVO) {
		boolean result = false;
		String name = memberVO.getMemberName();
		String pwd = memberVO.getMemberPassword();
	    Connection conn = null;
	    PreparedStatement pstmt = null;	    
	    try {
	    	conn = DBManager.getConnection();
			String sql = "select decode(count(*), 1, 'true', 'false') as result from member";
			sql += " where member_name=? and member_password=?";
	    	pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, name);
		    pstmt.setString(2, pwd);
		    ResultSet rs = pstmt.executeQuery();
		    rs.next();
		    result = Boolean.parseBoolean(rs.getString("result"));
		    System.out.println("result = " + result);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	DBManager.close(conn, pstmt);
	    }
		return result;
	}
}