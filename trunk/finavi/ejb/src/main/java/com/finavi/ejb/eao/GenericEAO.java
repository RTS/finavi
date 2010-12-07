package com.finavi.ejb.eao;

import java.util.List;

public interface GenericEAO<T> {
	public List<T> getAll();
	
	public T getById(long id);
	
	public T save(T arg);
	
	public T delete(T arg);
	
}
