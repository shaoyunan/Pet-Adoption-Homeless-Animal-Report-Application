package com.myspring.petfinder.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "petid", unique = true, nullable = false)
	private int petId;

	@Column(name = "petname")
	private String petName;

	@Column(name = "petage")
	private int petAge;

	@Column(name = "animaltype")
	private String animalType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "petownerid", nullable = false)
	private User owner;

	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private gender gender;

	@Column(name = "color")
	private String color;
	
	@Column(name = "breed")
	private String breed;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pet", orphanRemoval=true, cascade = CascadeType.ALL)
	private Set<IndividualRequest> requestList;
	
	@Column(name = "listed")
	private boolean listed;
	
	public Pet() {
	}

	public enum gender {
		MALE, FEMALE
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public int getPetAge() {
		return petAge;
	}

	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public gender getGender() {
		return gender;
	}

	public void setGender(gender gender) {
		this.gender = gender;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPetId() {
		return petId;
	}

	public Set<IndividualRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(Set<IndividualRequest> requestList) {
		this.requestList = requestList;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}
	
}
