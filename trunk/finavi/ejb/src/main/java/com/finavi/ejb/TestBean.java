package com.finavi.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finavi.ejb.thirdparty.MathEvaluator;
import com.finavi.model.Bank;
import com.finavi.model.LivingWages;
import com.finavi.model.Loan;
import com.finavi.model.LoanConditions;
import com.finavi.model.LoanFee;
import com.finavi.model.User;
@Remote(value=TestBeanRemote.class)
@Stateless(name="TestBean")
public class TestBean implements TestBeanLocal {
		
	public static final String RemoteJNDIName =  TestBean.class.getSimpleName() + "/remote";
	public static final String LocalJNDIName =  TestBean.class.getSimpleName() + "/local";
	
	@PersistenceContext 
    private EntityManager em;
 
    public TestBean(){
    	//initEmfAndEm();
    }
  
	public void test() {
		User u = new User();
		u.setName("matus");
		u.setDateOfBirth(new Date());
		em.persist(u);
		MathEvaluator m = new MathEvaluator("-5-6/(-2) + sqr(15+x)");
		m.addVariable("x", 15.1d);
		System.out.println( m.getValue() );
		 
	}

	@Override
	public void test2() {
		Bank b = new Bank();
		b.setCity("Bratislava");
		b.setEmail("info@tatrabankab.sk");
		b.setName("Tatrabanka");
		b.setPhone("021113333");
		b.setPostalCode("84501");
		b.setStreet("Horna");
		b.setStreetNumber("3/b");
		b.setWebsite("http://www.tatrabanka.sk");
		em.persist(b);
		Loan l = new Loan();
		l.setAccountManagementFee(15.5d);
		l.setMaxAmount(140000);
		l.setMinAmount(30000);
		l.setMinYears(5);
		l.setMaxYears(30);
		LivingWages lw1 = new LivingWages();
		lw1.setCoupleExpr("x-100");
		lw1.setCoupleWithOneChildExpr("299");
		lw1.setCoupleWithTwoChildExpr("400");
		lw1.setSingleExpr("single");
		lw1.setSingleWithOneChildExpr("34");
		LivingWages lw2 = new LivingWages();
		lw2.setCoupleExpr("x-100");
		lw2.setCoupleWithOneChildExpr("299");
		lw2.setCoupleWithTwoChildExpr("400");
		lw2.setSingleExpr("single");
		lw2.setSingleWithOneChildExpr("34");
		Set<LivingWages> list = new HashSet<LivingWages>();
		list.add(lw2);
		list.add(lw1);
		l.setLivingWagesRates(list);
		LoanConditions cond = new LoanConditions();
		cond.setFixation(1);
		cond.setInterestRateNoClient(1.5d);
		cond.setInterestRateWhenClient(3);
		LoanConditions cond2 = new LoanConditions();
		cond2.setFixation(2);
		cond2.setInterestRateNoClient(1.5d);
		cond2.setInterestRateWhenClient(3);
		LoanConditions cond3 = new LoanConditions();
		cond3.setFixation(3);
		cond3.setInterestRateNoClient(1.5d);
		cond3.setInterestRateWhenClient(3);
		Set<LoanConditions> conds = new HashSet<LoanConditions>();
		conds.add(cond3);
		conds.add(cond2);
		conds.add(cond);
		l.setConditions(conds);
		LoanFee f = new LoanFee();
		f.setBasedOnLoanAmount(true);
		f.setFee(234);
		l.setLoanFee(f);
		em.persist(l);
		b.setLoan(l);
		em.persist(b);
	}
}
