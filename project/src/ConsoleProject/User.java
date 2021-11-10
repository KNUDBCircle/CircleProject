package ConsoleProject;

import java.sql.Date;

public class User {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String did;
	private Date birth_date;
	
	public User(String id, String pwd, String name, String email, String did, Date birth_date) {
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
	
	public Date getUserBirthDate() {
		return birth_date;
	}

	public void setUserName(String name) {
		this.name = name;
	}

	public void setUserEmail(String email) {
		this.email = email;
	}

	public void setUserDid(String did) {
		this.did = did;
	}

	public void setUserBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public void setUserPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}


//user는 phonenumber 추가한거빼고 똑같음,,