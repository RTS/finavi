package com.finavi.ejb.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.finavi.model.User;

public class UserEAO implements GenericEAO<User> {

	private EntityManager em;

	public UserEAO (EntityManager em){
		this.em= em;
	}
	
	@Override
	public List<User> getAll() {
		Query q = em.createQuery("from User");
		return (List<User>)q.getResultList();
	}

	@Override
	public User getById(long id) {
		return em.find(User.class, id);
	}

	@Override
	public User save(User arg) {
		return em.merge(arg);
	}

	@Override
	public User delete(User arg) {
		em.remove(arg);
		return arg;
	}
	
	public List<User> findByName(String name) {
		Query q = em.createQuery("from User u where u.userName = :userName");
		q.setParameter("userName", name);
		return (List<User>)q.getResultList();
	}
	
	public List<User> findByEmail(String mail) {
		Query q = em.createQuery("from User u where u.email = :mail");
		q.setParameter("mail", mail);
		return (List<User>)q.getResultList();
	}
	
}
