package com.finavi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 6819280874413951096L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String userName;
	
	private String password;
	
	private String name;
	
	private String surname;
	
	private Date dateOfBirth;
	
	private char sex;
	
	private String personalNumber;
	
	private String street;
	
	private String streetNumber;
	
	private String city;
	
	private String postalCode;
	
	private String phone;
	
	private String email;
	
	private String highestEducation;
	
	private String employment;
	
	private boolean alreadyClientOfBank;
	
	@OneToMany(mappedBy="applicant", cascade=CascadeType.ALL)
    private Set<Scoring> scorings;
	
	private long clientSinceYear;
	@OneToOne(optional=true)
	@JoinColumn(name="bank_fk")
	private Bank bank;
	@OneToOne(optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name="agent_fk")
	private User agent;
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	private Set<Role> roles;
	
	public User() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public boolean isAlreadyClientOfBank() {
		return alreadyClientOfBank;
	}

	public void setAlreadyClientOfBank(boolean alreadyClientOfBank) {
		this.alreadyClientOfBank = alreadyClientOfBank;
	}

	public long getClientSinceYear() {
		return clientSinceYear;
	}

	public void setClientSinceYear(long clientSinceYear) {
		this.clientSinceYear = clientSinceYear;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setScorings(Set<Scoring> scorings) {
		this.scorings = scorings;
	}

	public Set<Scoring> getScorings() {
		return scorings;
	}
	
	public static boolean isUserInRole(User user, String role) {
		for (Iterator<Role> roles = user.getRoles().iterator(); roles.hasNext();) {
			Role type = (Role) roles.next();
			if (type.getName().equalsIgnoreCase(role)) {
				return true;
			}
		}
		return false;
	}
}
