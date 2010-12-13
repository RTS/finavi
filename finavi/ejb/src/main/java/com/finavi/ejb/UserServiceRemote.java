package com.finavi.ejb;

import java.util.List;

import com.finavi.model.User;

public interface UserServiceRemote {
	/**
	 * Vrati zoznam pouzivatelov.
	 * @return list pouzivatelov.
	 */
	public List<User> getAll();
	/**
	 * najde pouzivatela podla id.
	 * @param id id pouzivatela
	 * @return instancia pouzivatela.
	 */
	public User getById(long id);
	/**
	 * Pridat noveho pouzivatela.
	 * @param user udaje o pouzivatelovi.
	 * @return isntancia pouzivatela.
	 */
	public User add(User user);
	/**
	 * Upravi data o pouzivatelovi.
	 * @param user pouzivatel na updaveni.
	 * @return Upravena entita.
	 */
	public User update(User user);
	
	/**
	 * zmaze pouzivatela.
	 * @param user pozuivatel na zmazanie
	 * @return Vymazana entita.
	 */
	public User delete(User user);
	/**
	 * Najde pouzivatela podla username;
	 * @param name username pouzivatela.
	 * @return entitu pouzivatela.
	 */
	public User getByName(String name);
	
	/**
	 * najde pouzivatela podla emailu. 
	 * @param email email pouzivatela.
	 * @return entita pouzivatela
	 */
	public User getByEmail(String email);
	
	/**
	 * Vytvori noveho pouzivatela.
	 * @param user Udaje o pouzivatelovi. 
	 * @return vrati ci prebehla v poriadku.
	 */
	public User register(User user);
	/**
	 * User login. 
	 * 
	 * @param email Email pouzivatela
	 * @param password heslo pouzivatela
	 * @return Vrati instanciu pouzivatela ak prihlasenie prebehlo ok, Inak vrati Null;
	 */
	public User login(String email, String password);
	/**
	 * Vyhladavanie v databaze podla mena a priezviska.
	 * @param name krstne meno alebo cast
	 * @param surname priezvisko alebo jeho cast.
	 * @return list pouzivatelov ktory vyhovuju kriteriam.
	 */
	public List<User> search(String name, String surname);
}
