package ConsoleProject;
import chanwoo.*;
import java.sql.*;
import java.util.Scanner;

public class Project{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input;
		UserManager um = new UserManager();
		User user = null;
		DBHelper.getInstance();
		
		while(true) {
			System.out.println("Select menu");
			System.out.println("1. Sign In\n2. Sign Up\n3. exit");
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
				}
				else {
					System.out.println("Fail to Create User");
				}
				//break;
			}
			else if(input.equals("3")) break;
		}
	}
	
	public static void afterSignIn() {
//		System.out.println("1.Create/submit");
//		System.out.println("2.Enter circle");
//		Circle circle = new Circle(conn, stmt, user);
//		circle.circlePage(conn, stmt, user);
	}
}
