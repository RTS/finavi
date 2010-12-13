package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.User;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class UserTablePage extends AutheticatedPage {

	public UserTablePage() {
		SortableDataProvider<User> provider = new SortableDataProvider<User>() {

			private static final long serialVersionUID = 1L;
			List<User> list = new ArrayList<User>(FinaviService
					.getUserService().getAll());

			@Override
			public Iterator<? extends User> iterator(int arg0, int arg1) {
				return list.iterator();
			}

			@Override
			public IModel<User> model(User arg0) {
				return new Model<User>(arg0);
			}

			@Override
			public int size() {
				return list.size();
			}
		};

		List<IColumn<User>> collumns = new ArrayList<IColumn<User>>();
		collumns.add(new PropertyColumn<User>(new Model<String>("Meno"), "name"));
		collumns.add(new TextFilteredPropertyColumn<User, String>(
				new Model<String>("Priezvisko"), "surname"));
		collumns.add(new PropertyColumn<User>(new Model<String>("Email"),
				"email"));

		DefaultDataTable<User> userTable = new DefaultDataTable<User>(
				"userListTable", collumns, provider, 30);

		add(userTable);
	}
}
