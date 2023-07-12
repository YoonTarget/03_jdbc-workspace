package com.kh.model.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.PhoneBook;

public class PhoneBookDao {
	
	public int insertPhone(Connection conn, PhoneBook pb) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO TB_PHONEBOOK VALUES (SEQ_PHONEBOOK.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pb.getUserName());
			pstmt.setInt(2, pb.getAge());
			pstmt.setString(3, pb.getAddress());
			pstmt.setString(4, pb.getPhone());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public ArrayList<PhoneBook> selectPhone(Connection conn) {
		
		ArrayList<PhoneBook> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM TB_PHONEBOOK ORDER BY USERNO";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new PhoneBook(rset.getInt("userno"), 
										rset.getString("username"), 
										rset.getInt("age"), 
										rset.getString("address"), 
										rset.getString("phone")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}

}
