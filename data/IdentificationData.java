package data;

public class IdentificationData {
	private String login;
	private String password;
	
	public IdentificationData(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}

}
