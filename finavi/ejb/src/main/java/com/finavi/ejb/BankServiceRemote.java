package com.finavi.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.finavi.model.Bank;
@Remote
public interface BankServiceRemote {
	/**
	 * Vrati list vsetkych bank.
	 * @return zoznam bank;
	 */
	public List<Bank> getAll();
	/**
	 * Vrati banku podla id.
	 * @param id id banky
	 * @return instancia banky.
	 */
	public Bank getById(long id) ;
	/**
	 * Upravi udaje o banke.
	 * @param bank banka na update
	 * @return upravena entita.
	 */
	public Bank updateBank(Bank bank) ;
	/**
	 * prida banku.
	 * @param bank Udaje o banke.
	 * @return Nova entita banky.
	 */
	public Bank add(Bank bank);
}
