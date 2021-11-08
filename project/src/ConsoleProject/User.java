package ConsoleProject;

public class User{
	   private String id;
	   private String name;
	   private String phoneNum;
	   
	   public String getUserId() {
	      return id;
	   }
	   
	   public String getUserName() {
	      return name;
	   }
	   public String getUserPhoneNum() {
		      return phoneNum;
		   }
	   public User(String id, String name, String phoneNum) {
		   this.id = id;
		   this.name = name;
		   this.phoneNum = phoneNum;
	   }
}


//user는 phonenumber 추가한거빼고 똑같음,,