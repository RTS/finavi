import java.util.ArrayList;
import java.util.List;

import com.finavi.model.User;

public class UserController extends FinaviController{

	private String name;
	private String surname;
	private String mail;
	private String pass;
	private String city;
	private String username;
	private List<User> users = new ArrayList<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserController() {
		super();
	}

	public void register(){
		if (getUserService().getByName(username)!=null){
			System.out.println("already exist");
		}
		if (getUserService().getByEmail(mail)!=null){
			System.out.println("already exist");
		}
		User u = new User();
		u.setEmail(mail);
		u.setUserName(username);
		u.setPassword(pass);
		u.setName(name);
		u.setSurname(surname);
		getUserService().add(u);
	}

	public void save() {
		
	}
	
	public List<User> getUsers(){
		this.users = getUserService().getAll();
		return users;
	}
	
}
