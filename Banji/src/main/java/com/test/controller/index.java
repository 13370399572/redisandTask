package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class index {
    
	
	@GetMapping("/index/get")
	public String getIndex(HttpSession request ,Long id) {
		request.setAttribute("id", id);
		return "index.html";
	}
	
	@GetMapping("/index/getId")
	@ResponseBody
	public Long getId(HttpSession request) {
		if (request.getAttribute("id")!=null) {
			Long id= (Long)request.getAttribute("id");
			return id;
		}
		return null;
		
	}
 	
	@RequestMapping("/index/paginfo")
	public String select() {
		return "pagInfo.html";
	}
}
