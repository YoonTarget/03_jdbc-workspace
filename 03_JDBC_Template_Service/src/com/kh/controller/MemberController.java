package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

// Controller : View를 통해서 사용자가 요청한 기능에 대해서 처리하는 담당
// 				해당 메소드로 전달된 데이터 [가공처리 한 후] Dao로 전달하면서 호출
// 				Dao로부터 반환받은 결과에 따라 성공인지 실패인지 판단 후 응답화면 결정 (View 메소드 호출)
public class MemberController {
	
	//private MemberMenu mm = new MemberMenu();

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력했던 정보들이 담겨있는 매개변수
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
		//int result = new MemberDao().insertMember(m);
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원추가 되었습니다.");
		}
		else {
			new MemberMenu().displayFail("회원추가 실패했습니다.");
		}
		
	}
	
	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메소드
	 */
	public void selectList() {
		
		ArrayList<Member> list = new MemberService().selectList();
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("조회된 결과가 없습니다.");
		}
		else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	
	/**
	 * 사용자의 아이디로 회원 검색 요청을 처리해주는 메소드
	 * @param userId : 사용자가 입력한 검색하고자 하는 회원의 아이디 값
	 */
	public void selectByUserId(String userId) {
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m == null) {
			new MemberMenu().displayNoData("'" + userId + "'" + "에 해당하는 검색결과가 없습니다.");
		}
		else {
			new MemberMenu().displayMember(m);
		}
		
	}
	
	/**
	 * 이름으로 키워드 검색 요청시 처리해주는 메소드
	 * @param keyword
	 */
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("'" + keyword + "'" + "에 해당하는 검색결과가 없습니다.");
		}
		else {
			new MemberMenu().displayMemberList(list);
		}
	}
	
	/**
	 * 회원 탈퇴 요청 처리해주는 메소드
	 * @param userId : 사용자가 입력한 탈퇴시키고자 하는 회원 아이디 값
	 */
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
	
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원탈퇴가 되었습니다.");
		}
		else {
			new MemberMenu().displayFail("회원탈퇴에 실패했습니다.");
		}
	}
	
	/**
	 * 정보 변경 요청 처리해주는 메소드
	 * @param userId		: 변경하고자 하는 회원 아이디
	 * @param userPwd		: 변경할 비밀번호
	 * @param email			: 변경할 이메일
	 * @param phone			: 변경할 전화번호
	 * @param address		: 변경할 주소
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		//int result = new MemberDao().updateMember(userId, userPwd, email, phone, address);
		
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		int result = new MemberDao().updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보가 변경되었습니다.");
		}
		else {
			new MemberMenu().displayFail("회원정보 변경에 실패했습니다.");
		}
	}
	
	public void selectByName(String userName) {
		
		ArrayList<Member> list = new MemberService().selectByName(userName);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("'" + userName + "'" + "에 해당하는 검색결과가 없습니다.");
		}
		else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	
}
