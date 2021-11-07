package ConsoleProject;

public class User{
	   private String id;
	   private String name;
	   private String phoneNum;
	   
	   public String getId() {
	      return id;
	   }
	   
	   public String getName() {
	      return name;
	   }
	   public String getPhoneNum() {
		      return phoneNum;
		   }
	   public User(String id, String name, String phoneNum) {
		   this.id = id;
		   this.name = name;
		   this.phoneNum = phoneNum;
	   }
}
