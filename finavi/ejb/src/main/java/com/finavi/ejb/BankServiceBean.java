package com.finavi.ejb;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finavi.ejb.BankServiceLocal;
import com.finavi.ejb.eao.BankEAO;
import com.finavi.model.Bank;

@Local(value=BankServiceLocal.class)
@Remote(value=BankServiceRemote.class)
@Stateless(name="bankServiceBean")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BankServiceBean implements BankServiceLocal{
	@PersistenceContext
	EntityManager em;
	
	BankEAO eao;
	
	public BankServiceBean(){
	}
	
	private BankEAO getEAO(){
		if (this.eao==null){
			this.eao= new BankEAO(this.em);
		}
		return eao;
	}

	@Override
	public List<Bank> getAll()  {
		return getEAO().getAll();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Bank getById(long id)  {
		return getEAO().getById(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Bank updateBank(Bank bank)  {
		return getEAO().save(bank);
	}

	@Override
	public Bank add(Bank bank) {
		return getEAO().save(bank);
	}

}
