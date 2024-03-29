package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.request.target.component.ComponentRequestTarget;

import com.finavi.model.User;
import com.finavi.portal.page.base.AutheticatedPage;

public class UserTablePage extends AutheticatedPage {

	public UserTablePage() {
		
		final UserDataProvider provider = new UserDataProvider();
		
		Form<User> form = new Form<User>("userSearchForm",new CompoundPropertyModel<User>(new User())){
			private static final long serialVersionUID = -932846177693018756L;

			@Override
			protected void onSubmit() {
				provider.filter(getModelObject().getName(), getModelObject().getSurname());
			}
		};
		TextField<String> name = new TextField<String>("name");
		TextField<String> surname = new TextField<String>("surname");
		form.add(name);
		form.add(surname);
		add(form);
		
//		SortableDataProvider<User> provider = new SortableDataProvider<User>() {
//
//			private static final long serialVersionUID = 1L;
//			List<User> list = new ArrayList<User>(FinaviService
//					.getUserService().getAll());
//
//			@Override
//			public Iterator<? extends User> iterator(int arg0, int arg1) {
//				return list.iterator();
//			}
//
//			@Override
//			public IModel<User> model(User arg0) {
//				return new Model<User>(arg0);
//			}
//
//			@Override
//			public int size() {
//				return list.size();
//			}
//		};

		List<IColumn<User>> collumns = new ArrayList<IColumn<User>>();
		collumns.add(new PropertyColumn<User>(new Model<String>("Meno"), "name"));
		collumns.add(new TextFilteredPropertyColumn<User, String>(
				new Model<String>("Priezvisko"), "surname"));
		collumns.add(new PropertyColumn<User>(new Model<String>("Email"),
				"email"));
		/*collumns.add(new AbstractColumn<User>(new Model<String>("Scorings"))
			        {
			            public void populateItem(Item<ICellPopulator<User>> cellItem, String componentId,
			                final IModel<User> model)
			            {
			                cellItem.add(new Link(componentId) {
								@Override
								public void onClick() {
									setRedirect(true);
									setResponsePage(new ScoringTablePage(model.getObject()));
								}
							});
			               
			            }
			        });*/
		collumns.add(new AbstractColumn<User>(new Model<String>("scoring")) {
			private static final long serialVersionUID = -5381908354274974884L;

			public void populateItem(Item<ICellPopulator<User>> cellItem,
					String componentId, IModel<User> model) {
				cellItem.add(new ActionPanel(componentId, model));
			}
		});
		final DefaultDataTable<User> userTable = new DefaultDataTable<User>(
				"userListTable", collumns, provider, 30);

		add(userTable);
		
		Link<String> exportExcel = new Link<String>("exportExcel") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick() {
                getRequestCycle().setRequestTarget(new ComponentRequestTarget(userTable) {
                	@Override
                	   public void respond( RequestCycle requestCycle) {
                	        final int oldRowsPerPage = userTable.getRowsPerPage();
                	        userTable.setRowsPerPage (Integer.MAX_VALUE);
                	        super.respond(requestCycle);
                	        userTable.setRowsPerPage(oldRowsPerPage);
                	  }
                });
                WebResponse wr = (WebResponse) getResponse();
                wr.setContentType("application/vnd.ms-excel; charset=UTF-8");
                wr.setCharacterEncoding("UTF-8");
                wr.setHeader("content-disposition", "attachment;filename=export.xls");
            }
		};
		add(exportExcel);
	}
	class ActionPanel extends Panel {
		public ActionPanel(String id, IModel model) {
			super(id, model);
			add(new Link("scoring") {
				public void onClick() {
					setRedirect(true);
					setResponsePage(new ScoringTablePage((User) getParent().getDefaultModelObject()));
				}
			});
		}
	}
}
