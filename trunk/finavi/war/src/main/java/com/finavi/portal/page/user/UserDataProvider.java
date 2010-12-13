package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.User;
import com.finavi.portal.service.FinaviService;

public class UserDataProvider extends SortableDataProvider<User> {
	
	List<User> list = new ArrayList<User>(FinaviService
			.getUserService().getAll());
	
	@Override
	public Iterator<? extends User> iterator(int first,
			int count) {
		return list.iterator();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public IModel<User> model(User object) {
		return new Model<User>(object);
	}
	
	public void filter(String name, String surname) {
		list = FinaviService.getUserService().search(name, surname);
	}

	
}
