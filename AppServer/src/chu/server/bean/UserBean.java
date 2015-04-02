package chu.server.bean;

public class UserBean {
	private String name;
	private String password;
	
	public UserBean() {
		super();
	}

	public UserBean(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [name=" + name + ", password=" + password + "]";
	}
	
}
