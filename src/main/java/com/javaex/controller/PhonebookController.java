package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {

	//필드

	//생성자

	//메소드gs
	//메소드일반


	//등록폼
	//localhost:8080/phonebook5/phone/writeform
	@RequestMapping(value="/phone/writeform", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeform() {
		System.out.println("PhonebookController.writeform()");

		return "/WEB-INF/views/writeForm.jsp";

	}

	//등록
	//localhost:8080/phonebook5/phone/write?name=이미지&hp=010&company=02
	@RequestMapping(value="/phone/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam(value = "name") String name,
					  @RequestParam(value = "hp") String hp,
					  @RequestParam(value = "company") String company) {
		System.out.println("PhonebookController.write()");
		
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		
		//vo로 묶는다.
		PersonVo personvo = new PersonVo(name, hp, company);
		
		//dao를 메모리에 올린다.
		PhonebookDao phonebookDao= new PhonebookDao();
		
		//dao.personInsert(vo)를 저장한다.
		phonebookDao.personInsert(personvo);
		
		//리스트로 리다이렉트
		return "redirect/phone/list";
		
	}
	
	//등록2
	//localhost:8080/phonebook5/phone/write?name=이미지&hp=010&company=02
	@RequestMapping(value="/phone/write2", method= {RequestMethod.GET, RequestMethod.POST})
	public String write2(@ModelAttribute PersonVo personvo) {
		System.out.println("PhonebookController.write()");
		
		/*
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		*/
		
		//vo로 묶는다.
		//PersonVo personvo = new PersonVo(name, hp, company);
		System.out.println(personvo.toString());
		
		//dao를 메모리에 올린다.
		PhonebookDao phonebookDao= new PhonebookDao();
		
		//dao.personInsert(vo)를 저장한다.
		phonebookDao.personInsert(personvo);
		
		//리스트로 리다이렉트
		
		return "redirect:/phone/list";
	}
	
	//리스트
	@RequestMapping(value="/phone/list", method = {RequestMethod.GET, RequestMethod.GET})
		public String list(Model model) {
			System.out.println("PhonebookController.list()");
			
			PhonebookDao phonebookDao = new PhonebookDao();
			
			List<PersonVo> personList = phonebookDao.personSelect();
			//System.out.println(personList);
			model.addAttribute("pList", personList);
		
			return "/WEB-INF/views/list.jsp";
		}
}
