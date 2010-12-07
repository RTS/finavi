package com.finavi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoanConditions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8312864938833496397L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int fixation;
	
	private double interestRateWhenClient;
	
	private double interestRateNoClient;
	
	public LoanConditions(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFixation() {
		return fixation;
	}

	public void setFixation(int fixation) {
		this.fixation = fixation;
	}

	public double getInterestRateWhenClient() {
		return interestRateWhenClient;
	}

	public void setInterestRateWhenClient(double interestRateWhenClient) {
		this.interestRateWhenClient = interestRateWhenClient;
	}

	public double getInterestRateNoClient() {
		return interestRateNoClient;
	}

	public void setInterestRateNoClient(double interestRateNoClient) {
		this.interestRateNoClient = interestRateNoClient;
	}
}
