package ConsoleProject;

import java.sql.*;
import java.util.Scanner;
import SpecificCircle.*;

public class Project{
	static User user = null;
	static Scanner sc = new Scanner(System.in);
	static UserManager um = new UserManager();
	
	public static void main(String[] args) {
		
		DBHelper.getInstance();
		
		while(signphase()) {
			;
		}
		
	}
	
	public static boolean signphase() {
		String input;
		
		System.out.println("Select menu");
		System.out.println("1. Sign In\t2. Sign Up\t3. exit");
		input = sc.nextLine();
		if(input.equals("1")) {
			user = um.signInByConsole(sc);
			if(user != null) {
				afterSignIn();
			}
			else {
				System.out.println("Login Error! Check your ID/Password");
			}
			//break;
		}
		else if(input.equals("2")) {
			user = um.getUserInfoByConsole(sc);
			if(um.addUser(user) == 1) {
				System.out.println("Successfully Create User!");
				afterSignIn();
			}
			else {
				System.out.println("Fail to Create User");
			}
			//break;
		}
		else if(input.equals("3")) return false;
		return true;
	}
	
	public static void afterSignIn() {
//		System.out.println("1.Create/submit");
//		System.out.println("2.Enter circle");
//		Circle circle = new Circle(conn, stmt, user);
//		circle.circlePage(conn, stmt, user);
		String input;
		if(um.userConfig(sc, user) == 1) return;
	    enterCircle c1=new enterCircle("파도타기",user);
	     
	    c1.printMenu();
		
		
		
		System.out.println("나옴 !");
	}

}

//public class Project {
//	
//	public static void main(String[] args) {
//
//
//		User user1= new User("gsqogs074","하진","");
//	    enterCircle c1=new enterCircle("파도타기",user1);
//	     
//	    c1.printMenu();
//		
//		
//		
//		System.out.println("나옴 !");
//	    
//
//	}
//
//}
