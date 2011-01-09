package com.finavi.portal.page.bank;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.Bank;
import com.finavi.model.ScoringRequest;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class BankEvidencePage extends AutheticatedPage {
	// private final ListView<Bank> commentListView;
	private final WebMarkupContainer bankContainer;
	private Component name, website, email, street, streetNumber, city,
			postalCode, phone;
	private BankProvider provider = new BankProvider();
	protected Bank selected;
	BankForm form;

	public BankEvidencePage() {
		form = new BankForm("newbankForm");

		bankContainer = new WebMarkupContainer("banksListCont");

		List<IColumn<Bank>> collumns = new ArrayList<IColumn<Bank>>();
		collumns.add(new PropertyColumn<Bank>(new Model<String>("Meno"), "name"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("WEB"),
				"website"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("Email"),
				"email"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("Ulica"),
				"street"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("Čislo"),
				"streetNumber"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("Mesto"),
				"city"));
		collumns.add(new PropertyColumn<Bank>(new Model<String>("PSČ"),
				"postalCode"));
		collumns.add(new AbstractColumn<Bank>(new Model<String>("select")) {
			private static final long serialVersionUID = -5381908354274974884L;

			public void populateItem(Item<ICellPopulator<Bank>> cellItem,
					String componentId, IModel<Bank> model) {
				cellItem.add(new ActionPanel(componentId, model));
			}
		});
		final DefaultDataTable<Bank> bankTable = new DefaultDataTable<Bank>(
				"bankListTable", collumns, provider, 30);

		bankContainer.add(bankTable);

		form.add(new AjaxFormSubmitBehavior(form, "onsubmit") {
			private static final long serialVersionUID = -729417352056627219L;

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AjaxCallDecorator() {
					private static final long serialVersionUID = 1L;

					@Override
					public CharSequence decorateScript(CharSequence script) {
						return script + "return false;";
					}
				};
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				// add the list of components that need to be updated
				target.addComponent(bankContainer);
				// target.addComponent(text);

				// focus the textarea again
				target.appendJavascript("document.getElementById('"
						+ name.getMarkupId() + "').focus();");
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
			}
		});
		form.setVisible(false);
		add(form);
		add(bankContainer.setOutputMarkupId(true));
	}

	class ActionPanel extends Panel {
		private static final long serialVersionUID = 7355626822206566086L;

		public ActionPanel(String id, IModel model) {
			super(id, model);
			add(new Link("select") {
				public void onClick() {
					BankEvidencePage.this.selected = (Bank) getParent()
							.getDefaultModelObject();
					form.setModelObject(BankEvidencePage.this.selected);
					form.setVisible(true);
				}
			});
		}
	}

	public final class BankForm extends Form<Bank> {
		private static final long serialVersionUID = -3443415128075325383L;

		public BankForm(final String id) {
			super(id, new CompoundPropertyModel<Bank>(new Bank()));
			name = new TextField<String>("name").setOutputMarkupId(true);
			street = new TextField<String>("street").setOutputMarkupId(true);
			city = new TextField<String>("city").setOutputMarkupId(true);
			postalCode = new TextField<String>("postalCode")
					.setOutputMarkupId(true);
			streetNumber = new TextField<String>("streetNumber")
					.setOutputMarkupId(true);
			website = new TextField<String>("website").setOutputMarkupId(true);
			email = new TextField<String>("email").setOutputMarkupId(true);
			phone = new TextField<String>("phone").setOutputMarkupId(true);
			add(name);
			add(street);
			add(city);
			add(postalCode);
			add(streetNumber);
			add(website);
			add(email);
			add(phone);
		}

		@Override
		public final void onSubmit() {
			final Bank bank = getModelObject();
			final Bank newBank = bank;
			if (newBank.getId() > 0) {
				FinaviService.getBankService().updateBank(newBank);
			} else {
				FinaviService.getBankService().add(newBank);
			}
			provider.update();
			setVisible(false);
		}
	}

}