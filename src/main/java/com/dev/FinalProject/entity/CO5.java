package com.dev.FinalProject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CO5")
public class CO5 {
	@Id
	@GeneratedValue
	private Long id;
	private String studentid;
	private String studentname;
	private Double quiz;
	private Double classperf;
	private Double finalexam;
	private Double total;
	
	
	
	public CO5() {
		
	}
	public CO5(Long id, String studentid, String studentname, Double quiz, Double classperf, Double finalexam,
			Double total) {
		super();
		this.id = id;
		this.studentid = studentid;
		this.studentname = studentname;
		this.quiz = quiz;
		this.classperf = classperf;
		this.finalexam = finalexam;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public Double getQuiz() {
		return quiz;
	}
	public void setQuiz(Double quiz) {
		this.quiz = quiz;
	}
	public Double getClassperf() {
		return classperf;
	}
	public void setClassperf(Double classperf) {
		this.classperf = classperf;
	}
	public Double getFinalexam() {
		return finalexam;
	}
	public void setFinalexam(Double finalexam) {
		this.finalexam = finalexam;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}
