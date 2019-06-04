package com.myspring.petfinder.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.petfinder.dao.PetDAO;
import com.myspring.petfinder.dao.UserDAO;
import com.myspring.petfinder.pojo.Pet;
import com.myspring.petfinder.pojo.Pet.gender;
import com.myspring.petfinder.pojo.User;

@Controller
public class PetController {

	@RequestMapping(value = "/pet/delete.htm", method = RequestMethod.GET)
	public String deletePet(HttpServletRequest request, PetDAO petDao, UserDAO userDao, ModelMap map) {
		int id = Integer.parseInt(request.getParameter("petid"));

		try {
			Pet p = petDao.get(id);
			petDao.delete(p);
			// boolean isOk = userDao.refreshUser(u);
			// String un = u.getUserName();
			// u = userDao.get(un);
			// request.getSession().setAttribute("user", u);
			String un = String.valueOf(request.getSession().getAttribute("user"));
			User u = userDao.get(un);
			map.addAttribute("petList", u.getPetList());
			return "user-managepets";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/pet/addnewpet.htm", method = RequestMethod.POST)
	public String handleNewPet(HttpServletRequest request, UserDAO userDao, ModelMap map) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		Pet p = new Pet();
		p.setOwner(u);
		p.setAnimalType(request.getParameter("pettype"));
		p.setBreed(request.getParameter("petbreed"));
		p.setColor(request.getParameter("petcolor"));
		p.setDescription(request.getParameter("petdescription"));
		p.setLocation(request.getParameter("petlocation"));
		p.setPetAge(Integer.parseInt(request.getParameter("petage")));
		p.setPetName(request.getParameter("petname"));
		String sex = request.getParameter("petgender");
		p.setListed(true);
		if (sex.equals("MALE")) {
			p.setGender(gender.MALE);
		} else {
			p.setGender(gender.FEMALE);
		}

		try {
			userDao.registerPet(p);
			// User user = userDao.get(u.getUserName());
			// request.getSession().setAttribute("user", user);
			return "pet-addsuccess";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/pet/editpet.htm", method = RequestMethod.POST)
	public String handleEditPet(HttpServletRequest request, UserDAO userDao, PetDAO petDao, ModelMap map) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		Pet pet = null;
		for (Pet p : u.getPetList()) {
			if (p.getPetId() == Integer.parseInt(request.getParameter("petid"))) {
				pet = p;
			}
		}
		// Pet p = new Pet();
		// pet.setOwner(u);
		pet.setAnimalType(request.getParameter("pettype"));
		pet.setBreed(request.getParameter("petbreed"));
		pet.setColor(request.getParameter("petcolor"));
		pet.setDescription(request.getParameter("petdescription"));
		pet.setLocation(request.getParameter("petlocation"));
		pet.setPetAge(Integer.parseInt(request.getParameter("petage")));
		pet.setPetName(request.getParameter("petname"));
		String sex = request.getParameter("petgender");
		if (sex.equals("MALE")) {
			pet.setGender(gender.MALE);
		} else {
			pet.setGender(gender.FEMALE);
		}

		try {
			boolean isOk = petDao.updatePet(pet);
			if (isOk) {
				// request.getSession().setAttribute("user", u);
				u = userDao.get(un);
				map.addAttribute("petList", u.getPetList());
				return "user-managepets";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "null";
	}

	@RequestMapping(value = "/pet/view.htm", method = RequestMethod.GET)
	public String handleViewPet(HttpServletRequest request, PetDAO petDao, ModelMap map) {
		int petid = Integer.parseInt(request.getParameter("petid"));
		Pet pet = petDao.get(petid);
		map.addAttribute("pet", pet);
		map.addAttribute("option", "view");
		return "user-viewpet";
	}
}
