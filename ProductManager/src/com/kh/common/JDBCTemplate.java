package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	/**
	 * Connection 객체 생성(DB와 연결) 후 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			
			prop.load(new FileInputStream("resources/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("id"), 
											   prop.getProperty("pwd"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 트랜젝션 메소드
	
	/**
	 * Connection 객체를 전달받아서 commit 해주는 메소드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Connection 객체를 전달받아서 rollback 해주는 메소드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// close() 메소드
	
	/**
	 * Connection 객체를 반납하는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Statement 객체를 반납하는 메소드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ResultSet 객체를 반납하는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
