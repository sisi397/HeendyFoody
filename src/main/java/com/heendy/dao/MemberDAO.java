package com.heendy.dao;

import java.sql.CallableStatement;
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

import oracle.jdbc.OracleTypes;

/**
 * @author 문석호
 *  
 * 멤버 관련 DAO 클래스
 */
public class MemberDAO {
	private MemberDAO() { }//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//일반회원 회원가입 메서드
	public void addMember(MemberDTO memberVO) {
		Connection conn = null;
		CallableStatement cstmt = null;	    
		try {
			//DB 연결 및 callable 문장 호출
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_add_member(?,?,?,?,?,?)}");
			
			//?인자 세팅
			cstmt.setString(1, memberVO.getMemberName());
			cstmt.setString(2, memberVO.getMemberPassword());
			cstmt.setString(3, memberVO.getMemberEmail());
			cstmt.setString(4, memberVO.getAddress());
			cstmt.setInt(5, memberVO.getRoleId());
			cstmt.setString(6, memberVO.getBirthDate());
			
			//수행
			cstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
	}
	//아이디 비밀번호가 맞는지 체크 (로그인 메서드)
	public int isExisted(MemberDTO memberVO) {
		int result = -1;
		Connection conn = null;
		CallableStatement cstmt = null;	    
		try {
			//DB 연결 및 callable 문장 호출
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_member_IsExisted(?,?,?)}");
			
			//?인자 세팅
			cstmt.setString(1, memberVO.getMemberName());
			cstmt.setString(2, memberVO.getMemberPassword());
			cstmt.registerOutParameter(3, OracleTypes.INTEGER); //return 받을 위치와 타입 등록 
			cstmt.executeQuery(); 
			//1이면 로그인 성공 0이면 로그인 실패
			result = cstmt.getInt(3);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}
	
	//사용자 이메일과 이름으로 패스워드 찾기(비밀번호 찾기 기능)
	public String findMemberPassword(String name, String email) {
		String result = "";
		Connection conn = null;
		CallableStatement cstmt = null;	 
		try {
			//DB 연결 및 callable 문장 호출
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_member_findPw(?,?,?)}");
			//?인자 세팅
			cstmt.setString(1, name);
			cstmt.setString(2, email);
			cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
			//실행
			cstmt.executeQuery();
			
			//out인자 받아서 result에 저장
			result = cstmt.getString(3);
			
		//예외처리
		}catch (SQLException e) {
			result = "등록된 회원 정보가 없습니다.";
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}
	//사용자 이메일로 사용자 이름 찾기(아이디==이름 찾기 기능)
	public String findMemberId(String email) {
		String result = "";
		Connection conn = null;
		CallableStatement cstmt = null;	 
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_member_findId(?,?)}");
			cstmt.setString(1, email);
			cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
			
			//실행
			cstmt.executeQuery();
	
			result = cstmt.getString(2);

		}catch(SQLException e) {
			result = "가입한 아이디가 없습니다.";
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}

	//아이디 중복여부체크 (1이면 사용불가, 0이면 가능)
	public int duplicateId(String name) {
		int result = -1;
		Connection conn = null;
		CallableStatement cstmt = null;	 
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_member_duplicatedId(?,?)}");
			cstmt.setString(1, name);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			//실행
			cstmt.executeQuery();
			//결과 받아오기
			result = cstmt.getInt(2);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, cstmt);
		}
		return result;
	}

	//회원 불러오기 (사용자 이름으로 불러옴)
	public MemberDTO getMember(String name) {  
		MemberDTO memberVO= null;     
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			cstmt = conn.prepareCall("{call pkg_member.sp_member_getmember(?,?)}");
			cstmt.setString(1, name);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			//실행
			cstmt.executeQuery();
			//커서 객체 받아오기
			rs = (ResultSet)cstmt.getObject(2);
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
			DBManager.close(conn, cstmt, rs);
		}
		return memberVO;
	}
	
	//사용자 포인트 조회 메서드
	public int getMemberPoint(int member_id) throws SQLException {
		
		//변수 초기화
		int result = 0;
		
		//DB 연결 및 callable 문장 수행
		Connection conn = DBManager.getConnection();
		CallableStatement cstmt = conn.prepareCall("{call sp_select_member_point(?,?)}");
		
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

}