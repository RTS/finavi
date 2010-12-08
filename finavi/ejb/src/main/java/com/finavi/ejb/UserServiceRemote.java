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
	
	public boolean register(User user);
	
	public User login(String email, String password);
	
}
