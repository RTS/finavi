package com.finavi.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.finavi.model.Bank;
@Remote
public interface BankServiceRemote {
	
	public List<Bank> getAll();
	
	public Bank getById(long id) ;
	
	public Bank updateBank(Bank bank) ;
	
	public Bank add(Bank bank);
}
