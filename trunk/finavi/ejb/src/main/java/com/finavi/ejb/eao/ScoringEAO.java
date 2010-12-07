package com.finavi.ejb.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.finavi.model.Scoring;
import com.finavi.model.User;

public class ScoringEAO implements GenericEAO<Scoring> {

	private EntityManager em;

	public ScoringEAO(EntityManager em){
		this.em = em;
	}
	
	@Override
	public List<Scoring> getAll() {
		Query q = em.createQuery("from Scoring");
		return (List<Scoring>)q.getResultList();
	}

	@Override
	public Scoring getById(long id) {
		return em.find(Scoring.class, id);
	}

	@Override
	public Scoring save(Scoring arg) {
		return em.merge(arg);
	}

	@Override
	public Scoring delete(Scoring arg) {
		em.remove(arg);
		return arg;
	}
	
	public List<Scoring> getScoringForClient(User user){
		Query query = em.createQuery("SELECT s FROM Scoring s WHERE s.user = :user");
		query.setParameter("user",user);
		return (List<Scoring>)query.getResultList();
	}
}
