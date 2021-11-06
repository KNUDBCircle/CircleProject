package chanwoo;

public class User{
	   private String id;
	   private String name;
	   
	   public String getUserId() {
	      return id;
	   }
	   
	   public String getUserName() {
	      return name;
	   }
	   public User(String id, String name) {
		   this.id = id;
		   this.name = name;
	   }
}
