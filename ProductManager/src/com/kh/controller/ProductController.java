package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	/**
	 * 상품 조회 메소드
	 * @param pName
	 * @param menu
	 */
	public void selectProduct(String pName, int menu) {
		
		ArrayList<Product> list = new ProductService().selectProduct(pName, menu);
		
		if(list.isEmpty()) {
			new ProductMenu().displayResult("조회 결과가 없습니다.");
		}
		else {
			new ProductMenu().displayShowList(list);
		}
		
	}
	
	/**
	 * 상품 추가, 수정 메소드
	 * @param p
	 * @param menu
	 */
	public void inputProduct(Product p, int menu) {
		
		int result = new ProductService().inputProduct(p, menu);
		
		String message = "";
		String str = "";
		
		if(menu == 2) {
			str = "추가";
		}
		else {
			str = "수정";
		}
		
		if(result > 0) {
			message = "성공적으로 상품을 " + str + "했습니다.";
		}
		else {
			message = "상품 " + str + "에 실패했습니다.";
		}
		
		new ProductMenu().displayResult(message);
		
	}
	
	/**
	 * 상품 삭제 메소드
	 * @param productId
	 */
	public void deleteProduct(String productId) {
		
		int result = new ProductService().deleteProduct(productId);
		
		String message = "";
		
		if(result > 0) {
			message = "성공적으로 상품을 삭제했습니다.";
		}
		else {
			message = "상품 삭제에 실패했습니다.";
		}
		
		new ProductMenu().displayResult(message);
		
	}
	
}
