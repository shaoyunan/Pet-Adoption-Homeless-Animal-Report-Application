package com.myspring.petfinder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.petfinder.dao.PetDAO;
import com.myspring.petfinder.dao.ReportDAO;
import com.myspring.petfinder.dao.UserDAO;
import com.myspring.petfinder.pojo.HomelessReport;
import com.myspring.petfinder.pojo.HomelessReport.status;
import com.myspring.petfinder.pojo.Pet;

@Controller
public class AjaxController {

	@RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.GET)
	@ResponseBody
	public String ajaxService(HttpServletRequest request, PetDAO petDao) {
		String type = request.getParameter("type");
		String breed = request.getParameter("breed");
		String color = request.getParameter("color");
		String agefrom = request.getParameter("agefrom");
		String ageto = request.getParameter("ageto");
		String sex = request.getParameter("sex");

		String uid = String.valueOf(request.getSession().getAttribute("userid"));
		int userid = Integer.parseInt(uid);
		List<Pet> petlist = new ArrayList<Pet>();
		try {
			petlist = petDao.query(userid, type, breed, color, agefrom, ageto, sex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jarray = new JSONArray();
		for (Pet p : petlist) {
			JSONObject j = new JSONObject();
			j.put("petid", p.getPetId());
			j.put("petname", p.getPetName());
			j.put("petage", p.getPetAge());
			j.put("pettype", p.getAnimalType());
			j.put("sex", p.getGender());
			j.put("breed", p.getBreed());
			j.put("color", p.getColor());
			jarray.put(j);
		}
		// System.out.println(petlist.size());
		return jarray.toString();
	}

	@RequestMapping(value = "/validateusernameajax.htm", method = RequestMethod.GET)
	@ResponseBody
	public String validateUn(HttpServletRequest request, UserDAO userDao) {
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		int id = 0;
		if (userid != null) {
			id = Integer.parseInt(userid);
		}
		String result = "";
		if (!userDao.validateUsername(id, username)) {
			result += "Username already inuse";
		}
		return result;
	}

	@RequestMapping(value = "/mapservice.htm", method = RequestMethod.GET)
	@ResponseBody
	public String gmap(HttpServletRequest request, ReportDAO rDao) {
		try {
			List<HomelessReport> list = rDao.getList(status.PENDING);
			JSONArray jarray = new JSONArray();
			for (HomelessReport r : list) {
				JSONObject j = new JSONObject();
				j.put("type", r.getAnimaltype());
				j.put("location", r.getLocation());
				j.put("description", r.getMessage());
				j.put("priority", r.getPriority().toString());
				j.put("contact", r.getContact());
				jarray.put(j);
			}
			// System.out.println(petlist.size());
			return jarray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/petmapservice.htm", method = RequestMethod.GET)
	@ResponseBody
	public String pmap(HttpServletRequest request, PetDAO pDao) {
		try {
			int uid = Integer.parseInt(String.valueOf(request.getSession().getAttribute("userid")));
			List<Pet> list = pDao.getOthers(uid);
			JSONArray jarray = new JSONArray();
			for (Pet p : list) {
				JSONObject j = new JSONObject();
				j.put("type", p.getAnimalType());
				j.put("location", p.getLocation());
				j.put("breed", p.getBreed());
				j.put("description", p.getDescription());
				j.put("contact", p.getOwner().getPhoneNumber());
				jarray.put(j);
			}
			// System.out.println(petlist.size());
			return jarray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
