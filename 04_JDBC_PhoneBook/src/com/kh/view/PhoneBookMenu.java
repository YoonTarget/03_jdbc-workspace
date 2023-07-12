package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.PhoneBookController;
import com.kh.model.vo.PhoneBook;

public class PhoneBookMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private PhoneBookController pc = new PhoneBookController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n== 전화번호부 v1.0 ==\n");
			
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 검색");
			System.out.println("3. 전화번호 모두 보기");
			System.out.println("4. 종료");
			
			System.out.print("\n메뉴 >> ");
			
			int num = sc.nextInt();
			
			sc.nextLine();
			
			switch(num) {
			case 1 :
				inputPhone("== 전화번호 등록 ==");
				break;
			case 2 :
				break;
			case 3 :
				pc.selectPhone();
				break;
			case 4 :
				System.out.println("이용해 주셔서 감사합니다. 프로그램을 종료합니다.");
				return;
			default :
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
			
		}
		
		
	}
	
	public void inputPhone(String message) {
		
		System.out.println("\n" + message);
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("나이 : ");
		String age = sc.nextLine();
		
		System.out.print("주소('구'까지만) : ");
		String address = sc.nextLine();
		
		System.out.print("전화번호('-' 포함) : ");
		String phone = sc.nextLine();
		
		pc.insertPhone(userName, age, address, phone);
		
	}
	
	//================ 응답화면 ================
	
	public void displaySuccess(String message) {
		
		System.out.println("\n서비스 요청 성공 : " + message);
		
	}
	
	public void displayFail(String message) {
		
		System.out.println("\n서비스 요청 실패 : " + message);
	}
	
	public void displayNoData(String message) {
		
		System.out.println("\n검색 결과 : " + message);
		
	}
	
	public void displayShowList(ArrayList<PhoneBook> list) {
		
		
		
	}

}
