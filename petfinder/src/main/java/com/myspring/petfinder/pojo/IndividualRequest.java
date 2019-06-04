package com.myspring.petfinder.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "individualadoptionrequest")
public class IndividualRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requestid", unique = true, nullable = false)
	private int requestid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requestby", nullable = false)
	private User from;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private status status;
	
	@Column(name="requestdate")
	private Date requestdate;
	
	@Column(name="message")
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "petid", nullable = false)
	private Pet pet;
	
	public IndividualRequest() {}
	
	public enum status{
		PENDING, REJECTED, APPROVED
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public status getStatus() {
		return status;
	}

	public void setStatus(status status) {
		this.status = status;
	}

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRequestid() {
		return requestid;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
}
