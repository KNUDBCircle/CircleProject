package ConsoleProject;

import chanwoo.*;
import java.sql.*;

public class Project {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_COMPANY = "COMPANY";
	public static final String USER_PASSWD = "COMPANY";

	public static void main(String[] args) {

		DBHelper db = DBHelper.getInstance();

		// �α��� ���� ����

		User user = new User("ijcavr605", "�ÿ�", "01051971913");
		int choice = 1;

		// �α���
		// ȸ������


		Circle circle = new Circle(db, user);
		circle.circlePage();
		db.closeDBHelper();

	}

}
