package com.finavi.portal.page.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.finavi.model.Scoring;
import com.finavi.model.User;
import com.finavi.portal.application.FinaviSession;
import com.finavi.portal.page.base.AutheticatedPage;
import com.finavi.portal.service.FinaviService;

public class ScoringTablePage extends AutheticatedPage {

	public void init(final User user) {
		initSuccesfullScoringTable(user);
		initDeniedScoringTableForUser(user);
	}

	// for actual user
	public ScoringTablePage() {
		init(FinaviSession.get().getLoggedUser());
	}

	// for selected user
	public ScoringTablePage(User user) {
		init(user);
	}

	public void initSuccesfullScoringTable(final User user) {

		List<IColumn<Scoring>> collumns = new ArrayList<IColumn<Scoring>>();

		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Banka"), "bank"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("RPMN"), "rpmn"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Mesačná splátka"),
				"monthlyPayment"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Úroková sadzba"),
				"interestRate"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>(
						"Poplatok za vedenie účtu"),
				"accountFee"));
		collumns.add(new PropertyColumn<Scoring>(
				new Model<String>("Poplatok za vybavenie"),
				"loanProcessCharge"));

		SortableDataProvider<Scoring> provider = new SortableDataProvider<Scoring>() {

			private static final long serialVersionUID = 1L;
			List<Scoring> list = new ArrayList<Scoring>(
					FinaviService.getMorgageService()
							.getActualScoringsOfUser(user));

			@Override
			public Iterator<? extends Scoring> iterator(
					int arg0, int arg1) {
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
		DefaultDataTable<Scoring> scoringTable = new DefaultDataTable<Scoring>(
				"scoringTable", collumns, provider, 30);
		scoringTable.addTopToolbar(new NavigationToolbar(
				scoringTable));
		add(scoringTable);
	}

	public void initDeniedScoringTableForUser(
			final User user) {
		List<IColumn<Scoring>> deniedTableCollumns = new ArrayList<IColumn<Scoring>>();
		deniedTableCollumns
				.add(new PropertyColumn<Scoring>(
						new Model<String>("Banka"), "bank"));
		deniedTableCollumns
				.add(new PropertyColumn<Scoring>(
						new Model<String>(
								"Dôvod zamietnutia"),
						"denialReason"));

		SortableDataProvider<Scoring> deniedScoringsProvider = new SortableDataProvider<Scoring>() {

			private static final long serialVersionUID = 1L;
			List<Scoring> list = new ArrayList<Scoring>(
					FinaviService.getMorgageService()
							.getActualScoringsOfUser(user));
			List<Scoring> realList = setRealList();

			private List<Scoring> setRealList() {
				List<Scoring> denied = new ArrayList<Scoring>();
				for (Scoring s : list) {
					if (!s.isApproved()) {
						denied.add(s);
					}
				}
				return denied;
			}

			@Override
			public Iterator<? extends Scoring> iterator(
					int arg0, int arg1) {
				return realList.iterator();
			}

			@Override
			public IModel<Scoring> model(Scoring arg0) {
				return new Model<Scoring>(arg0);
			}

			@Override
			public int size() {
				return realList.size();
			}

		};
		DefaultDataTable<Scoring> deniedScorignsTable = new DefaultDataTable<Scoring>(
				"deniedScoringsTable", deniedTableCollumns,
				deniedScoringsProvider, 30);
		deniedScorignsTable
				.addTopToolbar(new NavigationToolbar(
						deniedScorignsTable));

		add(deniedScorignsTable);

	}

}
