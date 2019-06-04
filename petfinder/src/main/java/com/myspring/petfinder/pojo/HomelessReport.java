package com.myspring.petfinder.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="homelessreport")
public class HomelessReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportid", unique = true, nullable = false)
	private int reportid;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="reportdate")
	private Date reportdate;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	private status status;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="priority")
	private priority priority;
	
	@Column(name="message")
	private String message;
	
	@Column(name="animaltype")
	private String animaltype;
	
	@Enumerated(EnumType.STRING)
	@Column(name="healthcondition")
	private healthcondition healthcondition;
	
	@Column(name="location")
	private String location;
	
	@OneToOne(mappedBy="report", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private CompleteReport completeReport;
	
	@Column(name="aggressive")
	private boolean aggressive;
	
	@Column(name="indanger")
	private boolean indanger;
	
	
	public HomelessReport() {
		
	}
	
	public enum status {
		PENDING, COMPLETE
	}
	public enum priority {
		HIGH, MEDIUM, LOW
	}
	public enum healthcondition {
		GOOD, FAIR, POOR, CRITICAL
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getReportdate() {
		return reportdate;
	}
	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}
	public status getStatus() {
		return status;
	}
	public void setStatus(status status) {
		this.status = status;
	}
	public priority getPriority() {
		return priority;
	}
	public void setPriority(priority priority) {
		this.priority = priority;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAnimaltype() {
		return animaltype;
	}
	public void setAnimaltype(String animaltype) {
		this.animaltype = animaltype;
	}
	public healthcondition getHealthcondition() {
		return healthcondition;
	}
	public void setHealthcondition(healthcondition healthcondition) {
		this.healthcondition = healthcondition;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getReportid() {
		return reportid;
	}
	public CompleteReport getCompleteReport() {
		return completeReport;
	}
	public void setCompleteReport(CompleteReport completeReport) {
		this.completeReport = completeReport;
	}
	public boolean isAggressive() {
		return aggressive;
	}
	public void setAggressive(boolean aggressive) {
		this.aggressive = aggressive;
	}
	public boolean isIndanger() {
		return indanger;
	}
	public void setIndanger(boolean indanger) {
		this.indanger = indanger;
	}
	
	
}
