package com.finavi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class LivingWages implements Serializable{
	private static final long serialVersionUID = -8060996021966173169L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String singleExpr;
	
	private String coupleExpr;
	
	private String singleWithOneChildExpr;
	
	private String coupleWithOneChildExpr;
	
	private String coupleWithTwoChildExpr;

	public LivingWages(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSingleExpr() {
		return singleExpr;
	}

	public void setSingleExpr(String singleExpr) {
		this.singleExpr = singleExpr;
	}

	public String getCoupleExpr() {
		return coupleExpr;
	}

	public void setCoupleExpr(String coupleExpr) {
		this.coupleExpr = coupleExpr;
	}

	public String getSingleWithOneChildExpr() {
		return singleWithOneChildExpr;
	}

	public void setSingleWithOneChildExpr(String singleWithOneChildExpr) {
		this.singleWithOneChildExpr = singleWithOneChildExpr;
	}

	public String getCoupleWithOneChildExpr() {
		return coupleWithOneChildExpr;
	}

	public void setCoupleWithOneChildExpr(String coupleWithOneChildExpr) {
		this.coupleWithOneChildExpr = coupleWithOneChildExpr;
	}

	public String getCoupleWithTwoChildExpr() {
		return coupleWithTwoChildExpr;
	}

	public void setCoupleWithTwoChildExpr(String coupleWithTwoChildExpr) {
		this.coupleWithTwoChildExpr = coupleWithTwoChildExpr;
	}
	
}
