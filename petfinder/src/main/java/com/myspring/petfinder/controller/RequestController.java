package com.myspring.petfinder.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.petfinder.dao.PetDAO;
import com.myspring.petfinder.dao.RequestDAO;
import com.myspring.petfinder.dao.UserDAO;
import com.myspring.petfinder.pojo.IndividualRequest;
import com.myspring.petfinder.pojo.IndividualRequest.status;
import com.myspring.petfinder.pojo.Pet;
import com.myspring.petfinder.pojo.User;

@Controller
public class RequestController {

	@RequestMapping(value = "/request/getrequests.htm", method = RequestMethod.GET)
	public String getMessages(HttpServletRequest request, Model model, UserDAO userDao) {
		// String userid = request.getParameter("userid");
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		int option = Integer.parseInt(request.getParameter("option"));
		model.addAttribute("option", option);

		if (option == 1) {
			List<IndividualRequest> requestList = new ArrayList<IndividualRequest>();
			for (Pet p : u.getPetList()) {
				for (IndividualRequest ir : p.getRequestList()) {
					requestList.add(ir);
				}
			}
			model.addAttribute("requestList", requestList);
		} else {
			model.addAttribute("requestList", u.getRequestList());
		}
		return "user-viewrequests";
	}

	@RequestMapping(value = "/request/createrequest.htm", method = RequestMethod.GET)
	public String makeRequest(HttpServletRequest request, PetDAO petDao, Model model) {
		// String userid = request.getParameter("userid");
		model.addAttribute("receiver",
				petDao.get(Integer.parseInt(request.getParameter("petid"))).getOwner().getUserName());
		model.addAttribute("petid", Integer.parseInt(request.getParameter("petid")));
		model.addAttribute("action", "create");
		model.addAttribute("message", "");
		model.addAttribute("requestid", "");
		return "request-individualform";
	}

	@RequestMapping(value = { "/request/sendrequest.htm", "/request/updaterequest.htm" }, method = RequestMethod.POST)
	public String sendRequest(HttpServletRequest request, PetDAO petDao, Model model, UserDAO userDao,
			RequestDAO requestDao) {

		if (request.getParameter("action").equals("edit")) {
			try {
				requestDao.updateById(Integer.parseInt(request.getParameter("requestid")),
						request.getParameter("message"));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			int petid = Integer.parseInt(request.getParameter("petid"));
			String un = String.valueOf(request.getSession().getAttribute("user"));
			User u = userDao.get(un);
			int userid = u.getPetOwnerId();
			
			Pet pet = petDao.get(petid);

			IndividualRequest r = new IndividualRequest();
			r.setMessage(request.getParameter("message"));
			r.setStatus(IndividualRequest.status.PENDING);
			r.setRequestdate(new Date());
			r.setFrom(u);
			r.setPet(pet);
			try {
				if(!requestDao.validateRequest(petid, userid)) {
					model.addAttribute("message", "You have already sent a request to this pet");
					return "request-sent";
				}
				u.getRequestList().add(r);
				pet.getRequestList().add(r);
				requestDao.sendRequest(r);
				model.addAttribute("message", r);
				// request.getSession().setAttribute("user", u);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("message", "Your request has been sent.");
		return "request-sent";
	}

	@RequestMapping(value = "/request/edit.htm", method = RequestMethod.GET)
	public String editRequest(HttpServletRequest request, Model model, RequestDAO requestDao) {
		try {
			IndividualRequest ir = requestDao.getById(Integer.parseInt(request.getParameter("requestid")));
			model.addAttribute("receiver", request.getParameter("to"));
			model.addAttribute("petid", ir.getPet().getPetName());
			model.addAttribute("action", "edit");
			model.addAttribute("message", ir.getMessage());
			System.out.println(Integer.parseInt(request.getParameter("requestid")));
			model.addAttribute("requestid", Integer.parseInt(request.getParameter("requestid")));
			return "request-individualform";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/request/cancel.htm", method = RequestMethod.GET)
	public String cancelRequest(HttpServletRequest request, PetDAO petDao, Model model, UserDAO userDao,
			RequestDAO requestDao) {
		try {
			IndividualRequest ir = requestDao.getById(Integer.parseInt(request.getParameter("requestid")));
			// ir.getPet().setListed(true);
			// ir.getPet().getRequestList().remove(ir);
			String un = String.valueOf(request.getSession().getAttribute("user"));
			User u = userDao.get(un);
			// String un = u.getUserName();
			petDao.list(ir.getPet());
			requestDao.cancel(ir);
			// u = userDao.get(un);
			// userDao.refreshUser(u);
			int option = 0;
			model.addAttribute("option", option);
			// request.getSession().setAttribute("user", u);
			// outward requests
			if (option == 1) {
				List<IndividualRequest> requestList = new ArrayList<IndividualRequest>();
				for (Pet p : u.getPetList()) {
					for (IndividualRequest i : p.getRequestList()) {
						requestList.add(i);
					}
				}
				model.addAttribute("requestList", requestList);
			}

			return "user-viewrequests";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/request/view.htm", method = RequestMethod.GET)
	public String viewRequest(HttpServletRequest request, Model model, RequestDAO requestDao) {
		try {
			IndividualRequest ir = requestDao.getById(Integer.parseInt(request.getParameter("requestid")));
			model.addAttribute("petname", ir.getPet().getPetName());
			model.addAttribute("sender", ir.getFrom().getUserName());
			model.addAttribute("message", ir.getMessage());
			model.addAttribute("status", "noshow");
			if (ir.getStatus() == status.PENDING) {
				model.addAttribute("status", "show");
			}

			model.addAttribute("requestid", Integer.parseInt(request.getParameter("requestid")));
			return "request-process";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/request/process.htm", method = RequestMethod.GET)
	public String processRequest(HttpServletRequest request, PetDAO petDao, Model model, RequestDAO requestDao) {
		try {
			IndividualRequest ir = requestDao.getById(Integer.parseInt(request.getParameter("requestid")));
			String action = request.getParameter("action");
			if (action.equals("reject")) {
				requestDao.reject(ir);
			} else {
				requestDao.approve(ir);
				Pet p = ir.getPet();
				petDao.unlist(p);
				for (IndividualRequest indReq : ir.getPet().getRequestList()) {
					if (indReq.getRequestid() != ir.getRequestid()) {
						requestDao.reject(indReq);
					}
				}
			}
			
			return "request-processed";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
