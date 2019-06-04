package com.myspring.petfinder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.myspring.petfinder.dao.AnimalDAO;
import com.myspring.petfinder.dao.EmployeeDAO;

import com.myspring.petfinder.dao.ReportDAO;

import com.myspring.petfinder.pojo.Employee;
import com.myspring.petfinder.pojo.HomelessReport;

import com.myspring.petfinder.pojo.HomelessReport.status;
import com.myspring.petfinder.pojo.ShelterAnimal.gender;
import com.myspring.petfinder.pojo.ShelterAnimal;


@Controller
public class EmployeeController {

	@RequestMapping(value = "/employee/login.htm", method = RequestMethod.GET)
	public String showLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "employee-portal";
		}
		return "employee-login";
	}

	@RequestMapping(value = "/animal/add.htm", method = RequestMethod.GET)
	public String showAdd(ModelMap map) {
		map.addAttribute("option", "ADD");
		return "employee-viewanimal";
	}
	
	@RequestMapping(value = "/animal/add.htm", method = RequestMethod.POST)
	public String handleAdd(HttpServletRequest request, ModelMap map, AnimalDAO aDao) {
		try {
			map.addAttribute("from", 1);
			ShelterAnimal a = new ShelterAnimal();
			a.setType(request.getParameter("type"));
			a.setAge(Integer.parseInt(request.getParameter("age")));
			a.setBreed(request.getParameter("breed"));
			a.setGender(gender.MALE);
			if(request.getParameter("gender").equals("FEMALE")) {
				a.setGender(gender.FEMALE);
			}
			a.setColor(request.getParameter("color"));
			a.setName(request.getParameter("name"));
			a.setDescription(request.getParameter("description"));
			aDao.register(a);
			List<ShelterAnimal> reportList = null;
			reportList = aDao.getList();
			map.addAttribute("aList", reportList);
			return "employee-manageanimal";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/employee/login.htm", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, EmployeeDAO empDao, ReportDAO reportDao, ModelMap map) {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			Employee u = empDao.get(username, password);
			if (u != null) {
				session.setAttribute("user", u.getUsername());
				// session.setAttribute("userid", u.getPetOwnerId());
				int newRequest = 0;
				List<HomelessReport> list = reportDao.getList();
				for (HomelessReport hr : list) {

					if (hr.getStatus() == status.PENDING) {
						newRequest += 1;
					}

				}
				map.addAttribute("newRequest", newRequest);
				return "employee-portal";
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return "login-error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/employee/viewreports.htm", method = RequestMethod.GET)
	public String showReports(HttpServletRequest request, ReportDAO reportDao, ModelMap map) {
		int option = Integer.parseInt(request.getParameter("option"));

		try {
			List<HomelessReport> reportList = null;
			if (option == 0) {
				reportList = reportDao.getList();
				map.addAttribute("reportList", reportList);
			}
			return "employee-viewreport";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/employee/manageanimals.htm", method = RequestMethod.GET)
	public String manageAnimal(HttpServletRequest request, AnimalDAO aDao, ModelMap map) {
		map.addAttribute("from", 1);

		try {
			List<ShelterAnimal> reportList = null;

			reportList = aDao.getList();
			map.addAttribute("aList", reportList);

			return "employee-manageanimal";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/animal/edit.htm", method = RequestMethod.GET)
	public String handleViewPet(HttpServletRequest request, AnimalDAO aDao, ModelMap map) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ShelterAnimal a = aDao.get(id);
			map.addAttribute("animal", a);
			map.addAttribute("option", "EDIT");
			return "employee-viewanimal";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/animal/delete.htm", method = RequestMethod.GET)
	public String handleDeletePet(HttpServletRequest request, AnimalDAO aDao, ModelMap map) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ShelterAnimal a = aDao.get(id);
			aDao.delete(a);
			
			List<ShelterAnimal> reportList = null;
			map.addAttribute("from", 1);
			reportList = aDao.getList();
			map.addAttribute("aList", reportList);
			return "employee-manageanimal";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/animal/edit.htm", method = RequestMethod.POST)
	public String handleEditPet(HttpServletRequest request, AnimalDAO aDao, ModelMap map) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			map.addAttribute("from", 1);
			ShelterAnimal a = aDao.get(id);
			a.setAge(Integer.parseInt(request.getParameter("age")));
			a.setBreed(request.getParameter("breed"));
			a.setColor(request.getParameter("color"));
			a.setDescription(request.getParameter("description"));
			a.setName(request.getParameter("name"));
			a.setType(request.getParameter("type"));
			a.setGender(gender.MALE);
			if(request.getParameter("gender").equals("FEMALE")) {
				a.setGender(gender.FEMALE);
			}
			aDao.update(a);
			List<ShelterAnimal> reportList = null;

			reportList = aDao.getList();
			map.addAttribute("aList", reportList);
			return "employee-manageanimal";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}