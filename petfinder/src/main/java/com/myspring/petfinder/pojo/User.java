package com.myspring.petfinder.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "petowner")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petownerid", unique = true, nullable = false)
	private int petOwnerId;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private int status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", orphanRemoval=true, cascade = CascadeType.ALL)
	private Set<Pet> petList;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "from", cascade = CascadeType.ALL)
	private Set<IndividualRequest> requestList;
	
	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Pet> getPetList() {
		return petList;
	}

	public void setPetList(Set<Pet> petList) {
		this.petList = petList;
	}

	public int getPetOwnerId() {
		return petOwnerId;
	}

	public Set<IndividualRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(Set<IndividualRequest> requestList) {
		this.requestList = requestList;
	}

}
