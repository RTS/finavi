package com.finavi.ejb.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.finavi.model.Bank;

public class BankEAO implements GenericEAO<Bank> {

	EntityManager em;
	public BankEAO(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Bank> getAll() {
		Query q = em.createQuery("from Bank");
		return (List<Bank>)q.getResultList();
	}

	@Override
	public Bank getById(long id) {
		return em.find(Bank.class, id);
	}

	@Override
	public Bank save(Bank arg) {
		Bank bank = em.merge(arg);
		return bank;
	}

	@Override
	public Bank delete(Bank arg) {
		em.remove(arg);
		return arg;
	}

}
