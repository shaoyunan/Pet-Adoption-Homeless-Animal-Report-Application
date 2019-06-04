package com.myspring.petfinder.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.petfinder.dao.AnimalDAO;
import com.myspring.petfinder.dao.EmployeeDAO;
import com.myspring.petfinder.dao.ReportDAO;
import com.myspring.petfinder.pojo.CompleteReport;
import com.myspring.petfinder.pojo.Employee;
import com.myspring.petfinder.pojo.HomelessReport;
import com.myspring.petfinder.pojo.HomelessReport.healthcondition;
import com.myspring.petfinder.pojo.HomelessReport.priority;
import com.myspring.petfinder.pojo.HomelessReport.status;
import com.myspring.petfinder.pojo.ShelterAnimal.gender;
import com.myspring.petfinder.pojo.ShelterAnimal;
import com.myspring.petfinder.xls.XlsView;

@Controller
public class ReportController {

	public ReportController() {
	}

	@RequestMapping(value = "/user/report.htm", method = RequestMethod.GET)
	public String showReportForm(HttpServletRequest request) {
		return "user-homelessreport";
	}

	@RequestMapping(value = "/user/report.htm", method = RequestMethod.POST)
	public String handleReport(HttpServletRequest request, ReportDAO reportDao) {
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String aggressive = request.getParameter("aggressive");
		String food = request.getParameter("food");
		String danger = request.getParameter("danger");
		String contact = request.getParameter("contact");
		String health = request.getParameter("healthcondition");

		boolean hasfood = false;
		boolean indanger = false;
		boolean isaggressive = false;
		if (food != null) {
			hasfood = true;
		}
		if (danger != null) {
			indanger = true;
		}
		if (aggressive != null) {
			isaggressive = true;
			
		}

		HomelessReport report = new HomelessReport();
		if (health.equals("G")) {
			report.setHealthcondition(healthcondition.GOOD);
		} else if (health.equals("F")) {
			report.setHealthcondition(healthcondition.FAIR);
		} else if (health.equals("P")) {
			report.setHealthcondition(healthcondition.POOR);
		} else {
			report.setHealthcondition(healthcondition.CRITICAL);
		}

		report.setAnimaltype(type);
		report.setContact(contact);
		report.setLocation(location);
		report.setMessage(description);
		report.setReportdate(new Date());
		report.setPriority(priority.MEDIUM);
		report.setStatus(status.PENDING);
		report.setIndanger(indanger);
		report.setAggressive(isaggressive);
		if (!request.getParameter("healthcondition").equals("C") && hasfood) {
			report.setPriority(priority.LOW);
		}
		if (isaggressive || request.getParameter("healthcondition").equals("C") || indanger) {
			report.setPriority(priority.HIGH);
		}
		try {
			reportDao.create(report);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "report-success";
	}
	
	@RequestMapping(value = "/report/viewreport.htm", method = RequestMethod.GET)
	public String viewReport(HttpServletRequest request, ReportDAO reportDao, ModelMap map) {
		try {
			HomelessReport report = reportDao.getById(Integer.parseInt(request.getParameter("reportid")));
			map.addAttribute("report", report);
			if(report.getStatus()==status.PENDING) {
				map.addAttribute("editable", true);
			}else {
				CompleteReport subreport = reportDao.getComplete(report.getReportid());
				map.addAttribute("editable", false);
				//map.addAttribute("subreport", subreport);
				map.addAttribute("emp", subreport.getEmplopyee());
			}
			return "report-view";
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		return null;
	}
	
	@RequestMapping(value = "/report/export.xls", method = RequestMethod.GET)
	public ModelAndView exportReports(HttpServletRequest request, ReportDAO reportDao) {
		List<HomelessReport> reportList = null;
		try {
			reportList = reportDao.getList(status.PENDING);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView(new XlsView(), "list", reportList);
	}
	
	@RequestMapping(value = "/report/complete.htm", method = RequestMethod.GET)
	public String completeReport(HttpServletRequest request, ReportDAO reportDao, ModelMap map) {
		int reportid = Integer.parseInt(request.getParameter("reportid"));
		String animaltype = request.getParameter("type");
		map.addAttribute("reportid", reportid);
		map.addAttribute("type", animaltype);
		return "report-complete";
	}
	
	@RequestMapping(value = "/report/complete.htm", method = RequestMethod.POST)
	public String doCompleteReport(HttpServletRequest request, ReportDAO reportDao, ModelMap map, AnimalDAO aDao, EmployeeDAO eDao) {
		int reportid = Integer.parseInt(request.getParameter("reportid"));
		String username = String.valueOf(request.getSession().getAttribute("user"));
		try {
			Employee employee = eDao.get(username);
			HomelessReport report = reportDao.getById(reportid);
			CompleteReport subreport = new CompleteReport();
			subreport.setNote(request.getParameter("note"));
			subreport.setEmplopyee(employee);
			subreport.setReport(report);
			subreport.setFinishDate(new Date());
			report.setCompleteReport(subreport);
			report.setStatus(status.COMPLETE);
			reportDao.update(report);
			if(request.getParameter("add")!=null) {
				ShelterAnimal a = new ShelterAnimal();
				a.setType(request.getParameter("animaltype"));
				a.setAge(Integer.parseInt(request.getParameter("age")));
				a.setBreed(request.getParameter("petbreed"));
				a.setGender(gender.MALE);
				if(request.getParameter("petgender").equals("F")) {
					a.setGender(gender.FEMALE);
				}
				a.setColor(request.getParameter("petcolor"));
				a.setName(request.getParameter("name"));
				a.setDescription(request.getParameter("petdescription"));
				aDao.register(a);
			}
			
			return "report-updatesuccess";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/report/map.htm", method = RequestMethod.GET)
	public String showMap(HttpServletRequest request, ReportDAO reportDao, ModelMap map) {
		
		return "map-test";
	}
}
