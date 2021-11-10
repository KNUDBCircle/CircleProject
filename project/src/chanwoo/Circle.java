package chanwoo;

import ConsoleProject.*;
import java.sql.*;
import java.util.Scanner;

public class Circle {
	DBHelper db = null;
	User user = null;

	int id = 0;
	String cname = null;
	String manager = null;
	String phoneNum = null;
	String description = null;
	int maxPerson;
	String isCircle = null;
	int categoryId = 0;
	Date startDate;
	Date endDate;
	Scanner sc;

	public Circle(DBHelper db, User user) {
		this.db = db;
		this.user = user;
	}

	public void circlePage() {
		sc = new Scanner(System.in);
		System.out.println("1.Create Circle");
		System.out.println("2.Search Circle");
		System.out.println("3.Submit Circle");
		System.out.println("4.Enter Circle");
		System.out.println("-1.Go Back");
		int choice = sc.nextInt();
		while (choice != -1) {

			if (choice == 1)
				createCirclePage();

			if (choice == 2) {
				searchCirclePage();

			}
			if (choice == 3) {

			}
			if (choice == 4) {

			}
			System.out.println("1.Create Circle");
			System.out.println("2.Search Circle");
			System.out.println("3.Submit Circle");
			System.out.println("4.Enter Circle");
			System.out.println("-1.Go Back");
			choice = sc.nextInt();
		}
		sc.close();
	}

	public int searchCirclePage() {
		System.out.println("===========search Circle page==============");
		showCircleList();
		int choice;
		System.out.println("Input the ID of the circle you want to know: ");
		id = sc.nextInt();
		ResultSet rs = null;
		String sql = "select * from circle where id = " + String.valueOf(id);
		rs = db.runSql(sql);
		try {
			rs.next();
			cname = rs.getString(2);
			manager = rs.getString(3);
			phoneNum = rs.getString(4);
			description = rs.getString(5);
			maxPerson = rs.getInt(6);
			isCircle = rs.getString(7);
			categoryId = rs.getInt(8);
			startDate = rs.getDate(9);
			endDate = rs.getDate(10);
			rs.close();
			if (isCircle.equals("Y"))
				System.out.println("====Circle====");
			else
				System.out.println("=======Small Group========");
			System.out.println("name: " + cname);
			System.out.println("manager : " + manager);
			System.out.println("phoneNum: " + phoneNum);
			System.out.println("description: " + description);
			System.out.println("categry: " + Category.getCategoryName(db, categoryId));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();

		return 3;
	}

	public int createCirclePage() {
		// start-date end-date max_person,thumb_name, max_people�� nulló��
		// ���Ƹ��� �� phone num�� user�����ͷ� �ڵ�ó��

		System.out.println("===========create Circle page==============");
		System.out.print("Circle name: ");
		cname = sc.next();
		System.out.print("Description: ");
		description = sc.next();
		System.out.print("is Circle? (Y/N): ");
		isCircle = sc.next();
		Category.showCategory(db);
		System.out.print("Category id?: ");
		categoryId = sc.nextInt();
		manager = user.getUserId();
		id = getNewCircleId();

		String sql = getInsertCircleQuery();
		int res = db.updateSql(sql);
		System.out.println("=create complete=");

		return 3;
	}

	public String makeToken(String tempString) {
		if (tempString.matches(".*-.*"))
			tempString = "TO_DATE('" + tempString + "', 'yyyy-mm-dd'), ";
		else if (tempString.equals("NULL"))
			tempString += ", ";
		else if (tempString.matches(".*[a-zA-Zㄱ-ㅎㅏ-ㅣ가-R].*") || tempString.length() > 8)
			tempString = "'" + tempString + "', ";
		else
			tempString += ", ";
		return tempString;
	}

	public void showCircleList() {

		ResultSet rs = null;
		String sql = "select id,cname from Circle";
		rs = db.runSql(sql);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String cvalue = rs.getString(2);
				System.out.println(String.valueOf(id) + "  " + cvalue);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getNewCircleId() {
		int id = 0;
		ResultSet rs = null;

		try {
			String sql = "select max(id) from circle";
			rs = db.runSql(sql);
			rs.next();
			id = rs.getInt(1);
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return id + 1;
	}

	public String getInsertCircleQuery() {
		String sql = "INSERT INTO CIRCLE VALUES (";
		sql += makeToken(String.valueOf(id));
		sql += makeToken(cname);
		sql += makeToken(manager);
		sql += makeToken(phoneNum);
		sql += makeToken(description);
		sql += makeToken(String.valueOf(0));
		sql += makeToken(isCircle);
		sql += makeToken(String.valueOf(categoryId));
		sql += makeToken("NULL");
		sql += makeToken("NULL");
		sql += makeToken("NULL");
		sql = sql.substring(0, sql.length() - 2);
		sql += ")";
		System.out.println(sql);
		return sql;
	}
}
