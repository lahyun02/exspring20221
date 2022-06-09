package com.exam.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoJdbc implements MemberDao {
	// Data Access Object 
	// Java Object <-> DB Table . 자바 객체와 db와의 상호작용. 
	String url = "jdbc:oracle:thin:@localhost:1521:xe";	
	String user = "web";
	String password = "web01";
	
	//한번만 실행. 생성자를 만들거나 블록 안에 넣거나.
//	{
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	//db관련 코드와 ui관련 코드 분리
	@Override
	public List<MemberVo> selectMemberList() {
		// 데이터베이스 member 테이블의 정보를 select하여 출력 
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String selectSql = "SELECT mem_id, mem_pass, mem_name, mem_point FROM member";	
		try (	Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(selectSql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {	//다음 레코드가 있는 동안 반복 
				MemberVo vo = new MemberVo();
				vo.setMemId( rs.getString("mem_id") );
				vo.setMemPass( rs.getString("mem_pass") );
				vo.setMemName( rs.getString("mem_name") );
				vo.setMemPoint( rs.getInt("mem_point") );
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {
		int num = 0; 
		String insertSql = "INSERT INTO member "
				+ "(mem_id, mem_pass, mem_name, mem_point) "
				+ "VALUES (?, ?, ?, ?)"; 
		try (	
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(insertSql);				
		) {
			pstmt.setString(1, vo.getMemId());  
			pstmt.setString(2, vo.getMemPass());  
			pstmt.setString(3, vo.getMemName());  
			pstmt.setInt(4, vo.getMemPoint());  
			num = pstmt.executeUpdate();	//SQL문 실행결과 변경된 레코드 수를 반환 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int delMember(String memId) {
		// 입력한 아이디의 회원을 member 테이블에서 삭제
		int num = 0;
		String delSql = "DELETE FROM member WHERE mem_id = ?"; 
		try (	
				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(delSql);				
		) {
			pstmt.setString(1, memId);  
			num = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public MemberVo selectMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVo selectLoginMember(MemberVo vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
