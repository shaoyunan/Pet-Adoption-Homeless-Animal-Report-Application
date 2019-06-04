package com.myspring.petfinder.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.myspring.petfinder.dao.AnimalDAO;
import com.myspring.petfinder.dao.PetDAO;
import com.myspring.petfinder.dao.UserDAO;
import com.myspring.petfinder.pojo.Pet;
import com.myspring.petfinder.pojo.ShelterAnimal;
import com.myspring.petfinder.pojo.User;
import com.myspring.petfinder.pojo.IndividualRequest;
import com.myspring.petfinder.pojo.IndividualRequest.status;

@Controller
public class UserController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	public String showLoginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			return "user-dashboard";
		}
		return "user-login";
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	public String handleLoginForm(HttpServletRequest request, UserDAO userDao, ModelMap map) {
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User u = userDao.get(username, password);
			if (u != null && u.getStatus() == 1) {
				session.setAttribute("user", u.getUserName());
				session.setAttribute("userid", u.getPetOwnerId());
				int newRequest = 0;
				for (Pet p : u.getPetList()) {
					for (IndividualRequest ir : p.getRequestList()) {
						if (ir.getStatus() == status.PENDING) {
							newRequest += 1;
						}
					}
				}
				map.addAttribute("newRequest", newRequest);
				return "user-dashboard";
			} else if (u != null && u.getStatus() == 0) {
				map.addAttribute("errorMessage", "Please activate your account to login!");
				return "login-error";
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return "login-error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/user/register.htm", method = RequestMethod.GET)
	public String showCreateForm() {

		return "user-register-form";
	}

	@RequestMapping(value = "/user/create.htm", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request, UserDAO userDao, ModelMap map) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String useremail = request.getParameter("useremail");
		String password = request.getParameter("password");
		User user = new User();
		user.setUserName(username);
		user.setEmail(useremail);
		user.setPassword(password);
		user.setStatus(0);
		User u = null;
		try {
			u = userDao.get(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (u != null) {
			map.addAttribute("errorMessage", "username already exists");
			return "login-error";
		}
		try {
			u = userDao.register(user);
			Random rand = new Random();
			int randomNum1 = rand.nextInt(5000000);
			int randomNum2 = rand.nextInt(5000000);
			try {
				String str = "http://localhost:8080/petfinder/user/validateemail.htm?email=" + useremail + "&key1="
						+ randomNum1 + "&key2=" + randomNum2;
				session.setAttribute("key1", randomNum1);
				session.setAttribute("key2", randomNum2);
				sendEmail(useremail, "Click on this link to activate your account : " + str);
			} catch (Exception e) {
				System.out.println("Email cannot be sent");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "user-registered";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {

		return "forgot-password";
	}

	@RequestMapping(value = "/user/forgotpassword.htm", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request, UserDAO userDao) {

		String useremail = request.getParameter("useremail");

		User user = userDao.get(useremail);
		sendEmail(useremail, "Your password is : " + user.getPassword());
		return "forgot-password-success";

	}

	@RequestMapping(value = "user/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = request.getParameter("username");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			String str = "http://localhost:8080/lab10/user/validateemail.htm?email=" + useremail + "&key1=" + randomNum1
					+ "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail, "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		return "user-registered";
	}

	public void sendEmail(String username, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("info6250final@gmail.com", "syn930310"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(username);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	@RequestMapping(value = "user/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, UserDAO userDao, ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/lab10/user/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = userDao.updateUser(email);
				if (updateStatus) {
					return "user-login";
				} else {

					return "login-error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "login-error";
		}

		return "user-login";

	}

	@RequestMapping(value = "/user/managepets.htm", method = RequestMethod.GET)
	public String showManagePets(HttpServletRequest request, ModelMap map, UserDAO userDao) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);

		// System.out.println(u.getUserName());
		// Set<Pet> petList = u.getPetList();
		map.addAttribute("petList", u.getPetList());
		return "user-managepets";
	}

	@RequestMapping(value = "/user/dashboard.htm", method = RequestMethod.GET)
	public String showDashboard(HttpServletRequest request, ModelMap map, UserDAO userDao) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		int newRequest = 0;
		for (Pet p : u.getPetList()) {
			for (IndividualRequest ir : p.getRequestList()) {
				if (ir.getStatus() == status.PENDING) {
					newRequest += 1;
				}
			}
		}
		map.addAttribute("newRequest", newRequest);
		return "user-dashboard";
	}

	@RequestMapping(value = "/pet/addpet.htm", method = RequestMethod.GET)
	public String showAddpet(HttpServletRequest request, ModelMap map, PetDAO petDao) {
		if (request.getParameter("petid") != null) {
			map.addAttribute("option", "edit");
			map.addAttribute("petid", request.getParameter("petid"));
			map.addAttribute("pet", petDao.get(Integer.parseInt(request.getParameter("petid"))));
			return "user-addpet";
		}
		map.addAttribute("option", "add");
		return "user-addpet";
	}

	@RequestMapping(value = "/user/logout.htm", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "user-login";
	}

	@RequestMapping(value = "/user/editprofile.htm", method = RequestMethod.GET)
	public String editProfile(HttpServletRequest request, ModelMap map, UserDAO userDao) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		map.addAttribute("user", u);
		return "user-editprofile";
	}

	@RequestMapping(value = "/user/editprofile.htm", method = RequestMethod.POST)
	public String updateProfile(HttpServletRequest request, ModelMap map, UserDAO userDao) {
		String un = String.valueOf(request.getSession().getAttribute("user"));
		User u = userDao.get(un);
		u.setEmail(request.getParameter("email"));
		u.setFirstName(request.getParameter("fname"));
		u.setLastName(request.getParameter("lname"));
		u.setPassword(request.getParameter("password"));
		u.setPhoneNumber(request.getParameter("phone"));
		u.setUserName(request.getParameter("username"));
		try {
			userDao.changeUser(u);
			request.getSession().setAttribute("user", request.getParameter("username"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "user-dashboard";
	}
	
	@RequestMapping(value = "/list/browseshelterlist.htm", method = RequestMethod.GET)
	public String showShelterList(HttpServletRequest request, ModelMap map, AnimalDAO aDao) {
		map.addAttribute("from", 0);

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
	
	@RequestMapping(value = "/animal/view.htm", method = RequestMethod.GET)
	public String handleViewPet(HttpServletRequest request, AnimalDAO aDao, ModelMap map) {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ShelterAnimal a = aDao.get(id);
			map.addAttribute("animal", a);
			map.addAttribute("option", "VIEW");
			return "employee-viewanimal";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
