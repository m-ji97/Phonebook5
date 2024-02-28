package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhonebookDao {

	@Autowired
	private SqlSession sqlsession;

	// 전체가져오기
	public List<PersonVo> personSelect() {
		System.out.println("PhonebookDao.personSelect()");

		List<PersonVo> personlist = sqlsession.selectList("phonebook.select");
		System.out.println(personlist);

		return personlist;
	}

	// 등록
	public int personInsert(PersonVo personVo) {

		sqlsession.insert("phonebook.insert", personVo);
		System.out.println(personVo);

		return 0;
	}

	// 수정폼1개 가져오기
	// 1개 가져오기
	public PersonVo personSelectOne(int no) {

		PersonVo personVo = sqlsession.selectOne("phonebook.SelectOne", no);
		System.out.println(personVo);

		return personVo;
	}

	// 수정
	public int personUpdate(PersonVo personVo) {
		
		int count = sqlsession.update("phonebook.update", personVo);
		System.out.println(count);
		
		return count;
	}
	
	// 삭제
	public int personDelete(int no) {
		
		int count = sqlsession.delete("phonebook.delete", no);
		System.out.println(count);

		return count;
	}

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Autowired
	private DataSource dataSource;

	// 생성자
	// 메소드-gs

	// 메소드-일반

	// 연결
	public void getConnection() {
		try {

			// 2. Connection 얻어오기
			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

}