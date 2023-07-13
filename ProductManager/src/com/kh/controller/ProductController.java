package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	public void selectAll() {
		
		ArrayList<Product> list = new ProductService().selectAll();
		
		if(list.isEmpty()) {
			new ProductMenu().displayResult("조회 결과가 없습니다.");
		}
		else {
			new ProductMenu().displayShowList(list);
		}
		
	}
	
	public void insertProduct(Product p) {
		
		int result = new ProductService().insertProduct(p);
		
		String str = "";
		
		if(result > 0) {
			str = "성공적으로 상품을 추가했습니다.";
		}
		else {
			str = "상품 추가에 실패했습니다.";
		}
		
		new ProductMenu().displayResult(str);
		
	}
	
	public void updateProduct(Product p) {
		
		int result = new ProductService().updateProduct(p);
		
		String str = "";
		
		if(result > 0) {
			str = "성공적으로 상품을 수정했습니다.";
		}
		else {
			str = "상품 수정에 실패했습니다.";
		}
		
		new ProductMenu().displayResult(str);
		
	}
	
	public void deleteProduct(String productId) {
		
		int result = new ProductService().deleteProduct(productId);
		
		String str = "";
		
		if(result > 0) {
			str = "성공적으로 상품을 삭제했습니다.";
		}
		else {
			str = "상품 삭제에 실패했습니다.";
		}
		
		new ProductMenu().displayResult(str);
		
	}

}
