package com.finavi.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finavi.ejb.eao.UserEAO;
import com.finavi.model.User;
@Local(value=UserServiceLocal.class)
@Remote(value=UserServiceRemote.class)
@Stateless
public class UserServiceBean implements UserServiceLocal {
	@PersistenceContext
	EntityManager em;
	
	UserEAO eao;
	
	public UserServiceBean(){
	}

	@Override
	public List<User> getAll() {
		return eao.getAll();
	}

	@Override
	public User getById(long id) {
		return eao.getById(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User add(User user) {
		return eao.save(user);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User update(User user) {
		return eao.save(user);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User delete(User user) {
		return eao.delete(user);
	}

	@Override
	public User getByName(String name) {
		List<User> list = eao.findByName(name);
		if(list!=null&& list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getByEmail(String email) {
		List<User> list = eao.findByEmail(email);
		if(list!=null&& list.size()!=0){
			return list.get(0);
		}
		return null;
	}

}
