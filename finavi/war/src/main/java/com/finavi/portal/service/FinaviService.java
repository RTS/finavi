package com.finavi.portal.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.finavi.ejb.BankServiceLocal;
import com.finavi.ejb.MorgageServiceLocal;
import com.finavi.ejb.TestBeanLocal;
import com.finavi.ejb.UserServiceLocal;

public class FinaviService {
	
	 private static UserServiceLocal userService;
     
     private static BankServiceLocal bankService;
     
     private static MorgageServiceLocal morgageService;
     
     private  FinaviService(){
     }
     
	public static UserServiceLocal getUserService() {
		if (userService == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				userService = (UserServiceLocal) ctx.lookup("finavi-ear/UserServiceBean/local");
			} catch (NamingException e) {
				System.out.print("###EJB lookup failed!");
                throw new RuntimeException("couldn't lookup EJB beans", e);
			}
		}
		return userService;
	}
	public static BankServiceLocal getBankService() {
		if (bankService == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				bankService = (BankServiceLocal) ctx.lookup("finavi-ear/bankServiceBean/local");
			} catch (NamingException e) {
				System.out.print("###EJB lookup failed!");
                throw new RuntimeException("couldn't lookup EJB beans", e);
			}
		}
		return bankService;
	}
	public static MorgageServiceLocal getMorgageService() {
		if (morgageService == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				morgageService = (MorgageServiceLocal) ctx.lookup("finavi-ear/MorgageServiceBean/local");
			} catch (NamingException e) {
				System.out.print("###EJB lookup failed!");
                throw new RuntimeException("couldn't lookup EJB beans", e);
			}
		}
		return morgageService;
	}
     
}
