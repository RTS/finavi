package com.finavi.ejb;

import java.util.List;

import com.finavi.model.User;

public interface UserServiceRemote {
	public List<User> getAll();
	
	public User getById(long id);
	/**
	 * Pridat noveho pouzivatela.
	 * @param user
	 * @return
	 */
	public User add(User user);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User update(User user);
	
	public User delete(User user);
	
	public User getByName(String name);
	
	public User getByEmail(String email);
	
	/**
	 * Vytvori noveho pouzivatela.
	 * @param user Udaje o pouzivatelovi. 
	 * @return vrati ci prebehla v poriadku.
	 */
	public boolean register(User user);
	/**
	 * User login. 
	 * 
	 * @param email Email pouzivatela
	 * @param password heslo pouzivatela
	 * @return Vrati instanciu pouzivatela ak prihlasenie prebehlo ok, Inak vrati Null;
	 */
	public User login(String email, String password);
	
}
