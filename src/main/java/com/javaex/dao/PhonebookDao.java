package com.javaex.dao;

import java.util.List;
import java.util.Map;

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
	
	//등록2
	public int personInsert2(Map<String,String> pMap) {
		
		System.out.println("PhonebookDao.personInsert2()");
		System.out.println(pMap);
		
		int count = sqlsession.insert("phonebook.insert2",pMap);
		
		return count;
	}
	
	// 1개 가져오기2
	public Map<String, Object> personSelectOne2(int no) {
		System.out.println("PhonebookDao.personSelectOne2()");
		
		Map<String, Object> pMap = sqlsession.selectOne("phonebook.selectOne2", no);
			
		/*
		 * System.out.println(pMap.get("personId"));
		 * System.out.println(pMap.get("name")); System.out.println(pMap.get("hp"));
		 * System.out.println(pMap.get("company"));
		 */
		return pMap;
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

	

	

}