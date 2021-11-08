package ConsoleProject;

import chanwoo.*;
import java.sql.*;

public class Project {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_COMPANY = "COMPANY";
	public static final String USER_PASSWD = "COMPANY";

	public static void main(String[] args) {

		DBHelper db = DBHelper.getInstance();

		// 로그인 정보 저장

		User user = new User("ijcavr605", "시온", "01051971913");
		int choice = 1;

		// 로그인
		// 회원가입


		Circle circle = new Circle(db, user);
		circle.circlePage();
		db.closeDBHelper();

	}

}
