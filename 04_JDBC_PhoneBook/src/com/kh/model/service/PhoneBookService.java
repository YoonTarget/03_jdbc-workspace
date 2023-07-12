package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.model.dao.PhoneBookDao;
import com.kh.model.vo.PhoneBook;

public class PhoneBookService {
	
	public int insertPhone(PhoneBook pb) {
		
		Connection conn = getConnection();
		
		int result = new PhoneBookDao().insertPhone(conn, pb);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public ArrayList<PhoneBook> selectPhone() {
		
		Connection conn = getConnection();
		
		ArrayList<PhoneBook> list = new PhoneBookDao().selectPhone(conn);
		
		close(conn);
		
		return list;
		
	}

}
