import java.util.ArrayList;
import java.util.List;

import com.finavi.model.Bank;

public class BankController extends FinaviController{

	private String name;
	private String address;
	private String web;
	private String mail;
	private String city;
	private List<Bank> banks = new ArrayList<Bank>();

	public BankController() {
		super();
	}

	public void save() {
		Bank b = new Bank();
		getTestLocal().test2();
		b.setCity(city);
		b.setEmail(mail);
		b.setName(name);
		b.setWebsite(web);
		b.setStreet(address);
		getBankService().add(b);
	}

	public List<Bank> getBanks(){
		try {
			this.banks = getBankService().getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Bank b:banks){
			System.out.println(b);
			System.out.println(b.getLoan());
			if(b.getLoan()!=null){
				System.out.println(b.getLoan().getConditions());
			}
		}
		return this.banks;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
