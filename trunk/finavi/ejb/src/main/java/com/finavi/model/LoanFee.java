package com.finavi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoanFee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537314875120239803L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private boolean basedOnLoanAmount;
	
	private double fee;
	
	public LoanFee(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isBasedOnLoanAmount() {
		return basedOnLoanAmount;
	}

	public void setBasedOnLoanAmount(boolean basedOnLoanAmount) {
		this.basedOnLoanAmount = basedOnLoanAmount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
}
