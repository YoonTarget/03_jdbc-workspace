package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

// DAO (Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과받기(JDBC)
//							  결과를 Controller로 다시 리턴	
public class MemberDao {

	/*
	 * 기존의 방식 : DAO 클래스가 사용자가 요청할 때마다 실행해야되는 SQL문을 자바 소스코드 내에 명시적으로 작성 => 정적 코딩방식
	 * 
	 * > 문제점 : SQL문을 수정해야될 경우에 자바 소스코드를 수정해야됨 => 수정된 내용을 반영시키고자 한다면 반드시 프로그램을 재구동 해야됨!!
	 * 
	 * > 해결방식 : SQL문을 별도로 관리하는 외부파일(.xml)을 만들어서 실시간으로 그 파일에 기록된 sql문을 읽어들여서 실행 => 동적 코딩방식
	 * 			 여러줄 쓸 수 있도록 => xml로 하는 게 좋음
	 */
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 추가하는 메소드
	 * @param m
	 * @return result : 처리된 행수
	 */
	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행수 => 트랜젝션 처리
		
		int result = 0;
		// conn 이미 서비스에서 생성되어있음!!
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// 4,5) sql문 실행 및 결과 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*JDBCTemplate.*/close(pstmt);
			// conn은 아직 반납하면 안됨!! (트랜젝션 처리 서비스 가서 해야함!!)
		}
		
		return result;
		
	}
	
	/**
	 * 회원 전체 조회 메소드
	 * @return
	 */
	public ArrayList<Member> selectList(Connection conn) {
		// select문(여러행) => ResultSet 객체
		ArrayList<Member> list = new ArrayList<Member>(); // 텅빈 리스트
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("userno"), 
									rset.getString("userid"), 
									rset.getString("userpwd"), 
									rset.getString("username"), 
									rset.getString("gender"), 
									rset.getInt("age"), 
									rset.getString("email"), 
									rset.getString("phone"), 
									rset.getString("address"), 
									rset.getString("hobby"), 
									rset.getDate("enrolldate")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	
	/**
	 * 회원의 아이디를 검색해주는 메소드
	 * @param userId : 회원이 검색하고자 하는 아이디
	 * @return m : 그 아이디를 가진 회원
	 */
	public Member selectByUserId(Connection conn, String userId) {
		// select문 => 한행문 => ResultSet 객체 => Member 객체
		Member m = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByUserId");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("userno"), 
								rset.getString("userid"), 
								rset.getString("userpwd"), 
								rset.getString("username"), 
								rset.getString("gender"), 
								rset.getInt("age"), 
								rset.getString("email"), 
								rset.getString("phone"), 
								rset.getString("address"), 
								rset.getString("hobby"), 
								rset.getDate("enrolldate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
		
	}
	
	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'";
		// 위의 sql문을 실행할 경우 => pstmt.setString(1, "관");
		// => String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%'관'%'";
		
		// 해결방안 1.
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'"; // 하나의 문자열이 됨
		
		// 해결방안 2.
		String sql = prop.getProperty("selectByUserName");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			// 해결방안 1의 sql문일 경우
			// pstmt.setString(1, keyword); // '%관%'
			
			// 해결방안 2의 sql문일 경우
			pstmt.setString(1, "%" + keyword + "%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("userno"), 
									rset.getString("userid"), 
									rset.getString("userpwd"), 
									rset.getString("username"), 
									rset.getString("gender"), 
									rset.getInt("age"), 
									rset.getString("email"), 
									rset.getString("phone"), 
									rset.getString("address"), 
									rset.getString("hobby"), 
									rset.getDate("enrolldate")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	
	}
	
	/*
	public int updateMember(String userId, String userPwd, String email, String phone, String address) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ? WHERE USERID = ?";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, userId);
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	*/
	
	public int updateMember(Connection conn, Member m) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int deleteMember(Connection conn, String userId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public ArrayList<Member> selectByFullName(Connection conn, String userName) {
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectByFullName");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
	
				list.add(new Member(rset.getInt("userno"), 
									rset.getString("userid"), 
									rset.getString("userpwd"), 
									rset.getString("username"), 
									rset.getString("gender"), 
									rset.getInt("age"), 
									rset.getString("email"), 
									rset.getString("phone"), 
									rset.getString("address"), 
									rset.getString("hobby"), 
									rset.getDate("enrolldate")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close(rset);
		close(pstmt);
		
		return list;
		
	}
	
}
