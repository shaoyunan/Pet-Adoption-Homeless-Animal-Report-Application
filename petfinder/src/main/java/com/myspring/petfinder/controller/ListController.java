package com.myspring.petfinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.petfinder.dao.PetDAO;
import com.myspring.petfinder.dao.ReportDAO;
import com.myspring.petfinder.dao.UserDAO;
import com.myspring.petfinder.pojo.User;

@Controller
public class ListController {

	@RequestMapping(value = "/list/browseadoptionlist.htm", method = RequestMethod.GET)
	public String getMessages(HttpServletRequest request, Model model, PetDAO petDao, UserDAO userDao) {
		//String userid = request.getParameter("userid");
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		int option = Integer.parseInt(request.getParameter("option"));
		int page = Integer.parseInt(request.getParameter("page"));
		//outward requests
		if(option==0) {
			//individual list
			model.addAttribute("petList", petDao.getByPageI(page, u.getPetOwnerId()));
		}
		model.addAttribute("option", option);
		model.addAttribute("pages", petDao.getTotalPage(u.getPetOwnerId()));
		return "user-viewlist";
	}
	
	@RequestMapping(value = "/list/map.htm", method = RequestMethod.GET)
	public String showMap(HttpServletRequest request, ReportDAO reportDao, ModelMap map) {
		
		return "map-test2";
	}
}
