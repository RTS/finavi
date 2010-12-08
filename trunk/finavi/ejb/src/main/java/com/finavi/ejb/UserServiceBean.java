package com.finavi.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finavi.ejb.eao.BankEAO;
import com.finavi.ejb.eao.UserEAO;
import com.finavi.model.User;
/**
 * @author matus
 *
 */
@Local(value=UserServiceLocal.class)
@Remote(value=UserServiceRemote.class)
@Stateless
public class UserServiceBean implements UserServiceLocal {
	@PersistenceContext
	EntityManager em;
	
	UserEAO eao;
	
	public UserServiceBean(){
	}
	

	private UserEAO getEAO(){
		if (this.eao==null){
			this.eao= new UserEAO(this.em);
		}
		return eao;
	}

	@Override
	public List<User> getAll() {
		return getEAO().getAll();
	}

	@Override
	public User getById(long id) {
		return getEAO().getById(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User add(User user) {
		return getEAO().save(user);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User update(User user) {
		return getEAO().save(user);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public User delete(User user) {
		return getEAO().delete(user);
	}

	@Override
	public User getByName(String name) {
		List<User> list = getEAO().findByName(name);
		if(list!=null&& list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getByEmail(String email) {
		List<User> list = getEAO().findByEmail(email);
		if(list!=null&& list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean register(User user) {
		if (add(user)!=null){
			return true;
		}
		return false;
	}

	@Override
	public User login(String email, String password) {
		return getEAO().findByEmailAndPassword(email,password);
	}

}
