package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Product;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	/**
	 * xml 파일을 불러오는 메소드
	 */
	public ProductDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 상품 조회 메소드
	 * @param conn
	 * @param pName
	 * @param menu
	 * @return list
	 */
	public ArrayList<Product> selectProduct(Connection conn, String pName, int menu) {
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "";
		
		if(menu == 1) {
			query = "selectAll";
		}
		else {
			query = "selectByKewwordOfProduct";
		}
		
		String sql = prop.getProperty(query);
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			if(menu == 5) {
				pstmt.setString(1, pName);
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Product(rset.getString("PRODUCT_ID"), 
									 rset.getString("P_NAME"), 
									 rset.getInt("PRICE"), 
									 rset.getString("DESCRIPTION"), 
									 rset.getInt("STOCK")));
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
	 * 상품 추가, 수정 메소드
	 * @param conn
	 * @param p
	 * @param menu
	 * @return result
	 */
	public int inputProduct(Connection conn, Product p, int menu) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String query = "";
		
		int i = 0;
		int j = 0;
		
		if(menu == 2) {
			query = "insertProduct";
		}
		else {
			query = "updateProduct";
			i = 4;
			j = 1;
		}
		
		String sql = prop.getProperty(query);
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1 + i, p.getProductId());
			pstmt.setString(2 - j, p.getpName());
			pstmt.setInt(3 - j, p.getPrice());
			pstmt.setString(4 - j, p.getDescription());
			pstmt.setInt(5 - j, p.getStock());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	/**
	 * 상품 삭제 메소드
	 * @param conn
	 * @param productId
	 * @return result
	 */
	public int deleteProduct(Connection conn, String productId) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteProduct");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, productId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
}
