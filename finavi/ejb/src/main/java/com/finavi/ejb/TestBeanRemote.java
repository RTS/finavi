package com.finavi.ejb;

import javax.ejb.Remote;

@Remote
public interface TestBeanRemote {
	public void test();
	public void test2();
}