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
			
			int menu = sc.nextInt();
			
			sc.nextLine();
			
			switch(menu) {
			case 1 :
				System.out.println("== 전화번호 등록 ==");
				inputPhone();
				break;
			case 2 :
				System.out.println("== 전화번호 검색 ==");
				//pc.selectPhoneByNo(selectNo());
				break;
			case 3 :
				System.out.println("== 전화번호 전체조회 ==");
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
	
	public String selectNo() {
		
		System.out.print("전화번호('-' 포함) : ");
		
		return sc.nextLine();
	}
	
	public void inputPhone() {
		
		System.out.print("이름 : ");
		String userName = sc.nextLine();
		
		System.out.print("나이 : ");
		String age = sc.nextLine();
		
		System.out.print("주소('구'까지만) : ");
		String address = sc.nextLine();
		
		String phone = selectNo();
		
		PhoneBook pb = new PhoneBook(userName, Integer.parseInt(age), address, phone);
		
		pc.insertPhone(pb);
		
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
		
		System.out.println("\n검색 결과 : ");
		
		for(PhoneBook pb : list) {
			System.out.println(pb);
		}
		
	}

}
