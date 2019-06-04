package com.myspring.petfinder.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="reportfinished")
public class CompleteReport {

	@Id
	@Column(name="reportid", unique=true, nullable=false)
	@GeneratedValue(generator="gen")
	@GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="report"))
	private int reportid;
	
	@ManyToOne(fetch = FetchType.EAGER)  
	@JoinColumn(name="finishedby")
	@Fetch(FetchMode.SELECT)
	private Employee emplopyee;
	
	@Column(name="note")
	private String note;
	
	@Column(name="finishdate")
	private Date finishDate;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private HomelessReport report;
	
	public CompleteReport() {}

	public Employee getEmplopyee() {
		return emplopyee;
	}

	public void setEmplopyee(Employee emplopyee) {
		this.emplopyee = emplopyee;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getReportid() {
		return reportid;
	}

	public HomelessReport getReport() {
		return report;
	}

	public void setReport(HomelessReport report) {
		this.report = report;
	}
	
}
