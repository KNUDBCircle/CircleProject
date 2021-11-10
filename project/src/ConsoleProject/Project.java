package ConsoleProject;

import chanwoo.*;
import java.sql.*;
import java.util.Scanner;

import SpecificCircle.*;

public class Project {
	
	public static void main(String[] args) {


		User user1= new User("gsqogs074","하진","");
	    enterCircle c1=new enterCircle("파도타기",user1);
	     
	    c1.printMenu();
		
	
		System.out.println("나옴 !");
	    

	}

}
