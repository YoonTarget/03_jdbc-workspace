package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.PhoneBookService;
import com.kh.model.vo.PhoneBook;
import com.kh.view.PhoneBookMenu;

public class PhoneBookController {
	
	public void insertPhone(PhoneBook pb) {
		
		int result = new PhoneBookService().insertPhone(pb);
		
		if(result > 0) {
			new PhoneBookMenu().displaySuccess("성공적으로 전화번호를 등록했습니다.");
		}
		else {
			new PhoneBookMenu().displayFail("전화번호 등록에 실패했습니다.");
		}
		
	}
	
//	public void selectPhoneByNo(String phone) {
//		
//		PhoneBook pb = new PhoneBookService().selectPhoneByNo(phone);
//		
//		if(pb == null) {
//			new PhoneBookMenu().displayNoData("조회 결과가 없습니다.");
//		}
//		
//	}
	
	public void selectPhone() {
		
		ArrayList<PhoneBook> list = new PhoneBookService().selectPhone();
		
		if(list.isEmpty()) {
			new PhoneBookMenu().displayNoData("조회 결과가 없습니다.");
		}
		else {
			new PhoneBookMenu().displayShowList(list);
		}
		
	}

}
