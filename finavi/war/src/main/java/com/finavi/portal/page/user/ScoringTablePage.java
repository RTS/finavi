package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.Scoring;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class ScoringTablePage extends AutheticatedPage {

	public ScoringTablePage() {

		SortableDataProvider<Scoring> provider = new SortableDataProvider<Scoring>() {

			private static final long serialVersionUID = 1L;
			List<Scoring> list = new ArrayList<Scoring>(FinaviService
					.getMorgageService().getActualScoringsOfUser(
							FinaviSession.get().getLoggedUser()));

			@Override
			public Iterator<? extends Scoring> iterator(int arg0, int arg1) {
				return list.iterator();
			}

			@Override
			public IModel<Scoring> model(Scoring arg0) {
				return new Model<Scoring>(arg0);
			}

			@Override
			public int size() {
				return list.size();
			}
		};

		List<IColumn<Scoring>> collumns = new ArrayList<IColumn<Scoring>>();
		collumns.add(new PropertyColumn<Scoring>(new Model<String>("RPMN"),
				"rpmn"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Mesačná splátka"), "monthlyPayment"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Úroková sadzba"), "interestRate"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Poplatok účtu"), "accountFee"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Mesačná splátka"), "loanProcessCharge"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>("Vek"),
				"age"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Príjmy žiadateľa"), "incomeOfApplicant"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Výdavky žiadateľa"), "expensesOfApplicant"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Výška hypotéky"), "loanAmount"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Dĺžka splácania"), "repaymentPeriod"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>("Fixácia"),
				"fixation"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Hodnota nehnuteľnosti"), "realPropertyValue"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Rodinný stav"), "familyStatus"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Počet detí"), "numberOfChildren"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Počet závislých detí"), "numberOfDependentChildren"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Typ nehnuteľnosti"), "realPropertyType"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>("A"),
				"numberOfAdultPersons"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>("Ručiteľ"),
				"coApplicant"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Príjem ručiteľa"), "incomeOfCoApplicant"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Výdavky ručiteľa"), "expensesOfCoApplicant"));
		collumns.add(new PropertyColumn<Scoring>(new Model<String>(
				"Dôvod zamietnutia"), "denialReason"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Povolenie"), "approved"));

		DefaultDataTable<Scoring> scoringTable = new DefaultDataTable<Scoring>(
				"scoringTable", collumns, provider, 30);

		add(scoringTable);

	}

}
