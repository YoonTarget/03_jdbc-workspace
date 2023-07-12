package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n==== 제품 관리 프로그램 ====");
			
			System.out.println("1. 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 상품 수정 하기 (상품 id로 조회하고 수정)");
			System.out.println("4. 상품 삭제 하기 (상품 id로 조회해서 삭제)");
			System.out.println("5. 상품 검색 하기 (상품 이름으로 키워드 검색)");
			System.out.println("0. 프로그램 종료하기");
			
			System.out.print("\n메뉴 선택 >> ");
			
			int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			case 1 :
				pc.selectAll();
				break;
			case 2 :
				insertProduct(selectByProductId());
				break;
			case 3 :
				updateProduct(selectByProductId());
				break;
			case 4 :
				break;
			case 5 :
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다. 이용해 주셔서 감사합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
		
	}
	
	public void insertProduct(String productId) {
		
		System.out.print("상품명 : ");
		String pName = sc.nextLine();
		
		System.out.print("상품가격 : ");
		String price = sc.nextLine();
		
		System.out.print("상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("재고 : ");
		String stock = sc.nextLine();
		
		pc.insertProduct(new Product(productId, pName, Integer.parseInt(price), description, Integer.parseInt(stock)));
		
	}
	
	public void updateProduct(String productId) {
		
		System.out.print("변경할 상품명 : ");
		String pName = sc.nextLine();
		
		System.out.print("변경할 상품가격 : ");
		String price = sc.nextLine();
		
		System.out.print("변경할 상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print("변경할 재고 : ");
		String stock = sc.nextLine();
		
		pc.insertProduct(new Product(productId, pName, Integer.parseInt(price), description, Integer.parseInt(stock)));
	
	}
	
	public String selectByProductId() {
		
		System.out.print("상품 아이디 : ");
		return  sc.nextLine();
		
	}
	
	// ========================== 응답화면 ==========================
	
	public void displayResult(String message) {
		
		System.out.println("\n" + message);
		
		if(message.contains("성공")) {
			pc.selectAll();
		}
		
	}
	
	public void displayShowList(ArrayList<Product> list) {
		
		System.out.println("\n==== 조회 결과 ====");
		
		for(Product p : list) {
			System.out.println(p);
		}
		
	}
	
}
