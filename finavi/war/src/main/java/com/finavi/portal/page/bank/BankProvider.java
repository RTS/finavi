package com.finavi.portal.page.bank;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.Bank;
import com.finavi.portal.service.FinaviService;

public class BankProvider extends SortableDataProvider<Bank>{

	private static final long serialVersionUID = 615109451921964834L;
	
	private List<Bank> banks = FinaviService.getBankService().getAll();
	@Override
	public Iterator<? extends Bank> iterator(int arg0, int arg1) {
		return this.banks.iterator();
	}

	@Override
	public IModel<Bank> model(Bank arg0) {
		return new Model<Bank>(arg0);
	}

	@Override
	public int size() {
		return this.banks.size();
	}

	public void update() {
		this.banks=FinaviService.getBankService().getAll();
		
	}

}