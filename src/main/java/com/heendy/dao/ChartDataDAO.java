package com.heendy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import com.heendy.utils.DBManager;

import oracle.jdbc.OracleTypes;

public class ChartDataDAO {
	private ChartDataDAO() {  } //싱글턴 패턴 처리
    private static ChartDataDAO instance = new ChartDataDAO();
    public static ChartDataDAO getInstance() {
      return instance;
    }  
    
    // 오라클 연결
    private Connection conn;
    
    // sql문장전송, 함수 호출
    private CallableStatement cs;
    
    private ResultSet rs;
    
    // 구매자 연령층 data 가져오기
	public List<JSONObject> ageInfo(int cid) {
		List<JSONObject> memberList = new LinkedList<JSONObject>();
		
		String sql = "{CALL SP_MEMBER_AGEINFO(?,?)}";
	    
	    try {
	    	conn = DBManager.getConnection();
	    	cs = conn.prepareCall(sql);

		    cs.setInt(1, cid);
		    cs.registerOutParameter(2, OracleTypes.CURSOR);
		    
		    cs.executeUpdate();
		    
		    rs = (ResultSet)cs.getObject(2);
		    
		    JSONObject memberObj = null;
	        while (rs.next()) {
	        	String group = rs.getString("sorting");
	    	    int count = rs.getInt("count");
	    	    memberObj = new JSONObject();
	    	    memberObj.put("group", group);
	    	    memberObj.put("count", count);
	    	    memberList.add(memberObj);
	        }
		    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	DBManager.close(conn, cs);
	    }
	    return memberList;
	}
}
