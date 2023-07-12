package com.kh.model.vo;

public class PhoneBook {
	
	// 필드부
	private int userNo;
	private String userName;
	private int age;
	private String address;
	private String phone;
	
	// 생성자부
	// 기본생성자
	public PhoneBook() {}
	
	// 매개변수 생성자
	public PhoneBook(int userNo, String userName, int age, String address, String phone) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}
	
	// 매개변수 생성자(userNo 없는)
	public PhoneBook(String userName, int age, String address, String phone) {
		super();
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.phone = phone;
	}
	
	// 메소드부
	// getter-setter
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// toString()
	@Override
	public String toString() {
		return "PhoneBook [userNo=" + userNo + ", userName=" + userName + ", age=" + age + ", address=" + address
				+ ", phone=" + phone + "]";
	}
	
}
