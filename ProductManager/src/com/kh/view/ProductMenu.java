package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	private int menu;
	
	/**
	 * 메뉴 실행 화면
	 */
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
			
			menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			case 1 :
				System.out.println("\n==== 전체 조회 ====");
				pc.selectProduct("", menu); // "" => 메뉴 5번과 같은 메소드를 사용하기 때문에 매개변수를 전달해줘야 한다
				break;
			case 2 : case 3 : case 4:
				inputProduct();
				break;
			case 5 :
				System.out.println("\n==== 상품 검색 ====");
				System.out.print("상품 이름으로 키워드 검색 : ");
				pc.selectProduct(keyboard(), menu);
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다. 이용해 주셔서 감사합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
		
	}
	
	// ========================== 각 메뉴의 메소드들 ==========================
	
	/**
	 * 상품 id or 이름(키워드) 입력 메소드
	 * @return productId
	 */
	public String keyboard() {
		
		return sc.nextLine();
		
	}
	
	/**
	 * 상품 추가, 수정, 삭제 메소드
	 * @param message
	 */
	public void inputProduct() {
		
		String message = "";
		
		if(menu == 2) {
			message = "상품 추가";
		}
		else if(menu == 3) {
			message = "상품 수정";
		}
		else {
			message = "상품 삭제";
		}
		
		
		System.out.println("\n==== " + message +" ====");
		
		String update = "";
		
		if(menu == 3) {
			update = "변경할 ";
		}
		
		System.out.print("상품 아이디 : ");
		String productId = keyboard();
		
		if(menu == 4) {
			pc.deleteProduct(productId);
			return;
		}
		
		System.out.print(update + "상품명 : ");
		String pName = sc.nextLine();
		
		System.out.print(update + "상품가격 : ");
		String price = sc.nextLine();
		
		System.out.print(update + "상품 상세 정보 : ");
		String description = sc.nextLine();
		
		System.out.print(update + "재고 : ");
		String stock = sc.nextLine();
		
		pc.inputProduct(new Product(productId, pName, Integer.parseInt(price), description, Integer.parseInt(stock)), menu);
		
	}
	
	// ========================== 응답화면 ==========================
	
	/**
	 * DML문 실행 결과를 알려주는 메소드
	 * @param message
	 */
	public void displayResult(String message) {
		
		System.out.println("\n" + message);
		
		if(message.contains("성공")) {
			pc.selectProduct("", 1);
		}
		
	}
	
	/**
	 * SELECT문 실행에 성공했을 때 list를 출력하는 메소드
	 * @param list
	 */
	public void displayShowList(ArrayList<Product> list) {
		
		System.out.println("\n==== 조회 결과 ====");
		
		for(Product p : list) {
			System.out.println(p);
		}
		
	}
	
}
