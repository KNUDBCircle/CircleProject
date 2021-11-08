package ConsoleProject;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String did;
	private String birth_date;
	
	public User(String id, String pwd, String name, String email, String did, String birth_date) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.did = did;
		this.birth_date = birth_date;
	}
	
	public String getUserId() {
		return id;
	}
	
	public String getUserPwd() {
		return pwd;
	}
	
	public String getUserName() {
		return name;
	}
	
	public String getUserEmail() {
		return email;
	}
	
	public String getUserDid() {
		return did;
	}
	
	public String getUserBirthDate() {
		return birth_date;
	}
}
