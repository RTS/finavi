package com.finavi.ejb;

import javax.ejb.Remote;

import com.finavi.model.Role;

@Remote
public interface TestBeanRemote {
	public void test();
	public void test2();
	public void save(Object o);
	public <T>Object getById(Class<T> clazz , long id);
	public Role getRoleById(long id);
}