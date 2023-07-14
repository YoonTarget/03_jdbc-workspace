package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

public class ProductService {
	
	// 본 클래스 호출시 Connection 객체를 사용하여 DB와 연결하고 
	// 해당 객체를 전역변수로 지정하여 클래스 내의 모든 메소드에서 사용할 수 있도록 설정
	private Connection conn = getConnection();
	
	/**
	 * 상품 조회 메소드
	 * @param pName
	 * @param menu
	 * @return list
	 */
	public ArrayList<Product> selectProduct(String pName, int menu) {
		
		ArrayList<Product> list = new ProductDao().selectProduct(conn, pName, menu);
		
		close(conn);
		
		return list;
		
	}
	
	/**
	 * 상품 추가, 수정 메소드
	 * @param p
	 * @param menu
	 * @return result
	 */
	public int inputProduct(Product p, int menu) {
		
		int result = new ProductDao().inputProduct(conn, p, menu);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	/**
	 * 상품 삭제 메소드
	 * @param productId
	 * @return result
	 */
	public int deleteProduct(String productId) {
		
		int result = new ProductDao().deleteProduct(conn, productId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
}
