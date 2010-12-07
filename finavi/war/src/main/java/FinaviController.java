import javax.naming.InitialContext;

import com.finavi.ejb.BankServiceLocal;
import com.finavi.ejb.MorgageServiceLocal;
import com.finavi.ejb.TestBeanLocal;
import com.finavi.ejb.UserServiceLocal;

public class FinaviController {
	
	private UserServiceLocal userService;
	
	private BankServiceLocal bankService;
	
	private MorgageServiceLocal morgageService;
	
	private TestBeanLocal testLocal;
	
	public FinaviController(){
		init();
	}
	
	private void init(){
		try {
			InitialContext ctx = new InitialContext();
			bankService = (BankServiceLocal) ctx.lookup("finavi-ear/bankServiceBean/local");
			userService = (UserServiceLocal) ctx.lookup("finavi-ear/UserServiceBean/local");
			morgageService = (MorgageServiceLocal) ctx.lookup("finavi-ear/MorgageServiceBean/local");
			setTestLocal((TestBeanLocal)ctx.lookup("finavi-ear/TestBean/local"));
		} catch (Exception e) {
			System.out.print("###EJB lookup failed!");
			throw new RuntimeException("couldn't lookup EJB beans", e);
		}
	}
	
	public UserServiceLocal getUserService() {
		return userService;
	}

	public BankServiceLocal getBankService() {
		return bankService;
	}

	public MorgageServiceLocal getMorgageService() {
		return morgageService;
	}

	public void setTestLocal(TestBeanLocal testLocal) {
		this.testLocal = testLocal;
	}

	public TestBeanLocal getTestLocal() {
		return testLocal;
	}

}
