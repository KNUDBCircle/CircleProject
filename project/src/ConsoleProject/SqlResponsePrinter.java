package ConsoleProject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class SqlResponsePrinter {
	public void print(String sql) {
		DBHelper db = DBHelper.getInstance();
		
		ResultSet rs = db.runSql(sql);
		
		try {
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			System.out.print("||");
			for(int i = 1; i <= count; i++) {
				System.out.print("\t" + md.getColumnName(i) + "\t||");
			}
			System.out.println();
			while(rs.next()) {
				System.out.print("||");
				for(int i = 1; i <= count; i++) {
					System.out.print("\t" + rs.getString(i) + "\t||");
				}
				System.out.println();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			return;
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	public boolean selectSql(Scanner sc) {
		String input, sql = "";
		System.out.println("-------Statistic Page-------");
		System.out.println("Select menu");
		System.out.println("1. Post that wrote by specific user");
		System.out.println("2. Circle that specific category");
		System.out.println("3. Circle info with specific color of schedule");
		System.out.println("4. Circle, tab menu with specific category");
		System.out.println("5. Show group of users who joined several circle grouped by specific department");
		System.out.println("6. Group of comment which contains two or more comment that wrote by user from specific department");
		System.out.println("7. Circle that manages user from specific department");
		System.out.println("8. post that commented by specific user");
		System.out.println("9. Users who didn't write post in specific department");
		System.out.println("10. Post without comments in specific category");
		System.out.println("11. The name of the user who created the schedule with a specific color");
		System.out.println("12. The name of the circle that contains students from a specific department");
		System.out.println("13. ID and name of circle with more than a certain number of students");
		System.out.println("14. ID and name of department with more than a certain number of students");
		System.out.println("15. The name and start date of the circle that has members of a specific department");
		System.out.println("16. Post title and date of a specific circle");
		System.out.println("17. Number of Student by departments to which the circle manager belongs, whose circle membership start date is (MMDD)");
		System.out.println("18. Number of schedules by color written by members of circle where max_person is x~y");
		System.out.println("19. Departments that have not joined specific circle");
		System.out.println("20. The name of the circle with x or more members or with at least one post");
		System.out.println("21. Logout");
		input = sc.nextLine();
		
		switch(input) {
		case "1":
			System.out.print("Input User ID: ");
			input = sc.nextLine();
			sql = "SELECT Title, Content"
					+ " FROM Board"
					+ " WHERE User_id = '"+ input + "'";
			break;
		case "2":
			showCategory();
			System.out.print("Input category ID: ");
			input = sc.nextLine();
			sql = "SELECT Cname, Phone_num"
					+ " FROM CIRCLE"
					+ " WHERE Category_id = " + input;
			break;
		case "3":
			System.out.print("Select color(red / yellow / blue / purple / orange / green): ");
			input = sc.nextLine();
			sql = "SELECT c.Cname,u.Name"
					+ " FROM SCHEDULES s, CIRCLE c, USERS u"
					+ " WHERE s.Color = '" + input + "' AND s.Cid = c.Id"
					+ " AND  c.Manager = u.Id";
			break;
		case "4":
			showCategory();
			System.out.print("Input category ID: ");
			input = sc.nextLine();
			sql = "SELECT Cname,Tname"
					+ " FROM CIRCLE c,TAB_MENU t"
					+ " WHERE C.category_id = '" + input + "' AND t.Cid = c.Id";
			break;
		case "5":
			System.out.print("Input count of circles: ");
			input = sc.nextLine();
			sql = "SELECT Dep_name, count(*)"
					+ " FROM users u, department"
					+ " WHERE u.id  in "
					+ "	(SELECT user_id  "
					+ "    FROM belongs_to"
					+ "    GROUP BY user_id"
					+ "    HAVING count(*) >= " + input + ") "
					+ " AND Dep_id= u.Did"
					+ " GROUP BY Dep_name";
			break;
		case "6":
			UserManager.printDepartment();
			System.out.print("Input Department name: ");
			input = sc.nextLine();
			sql = "SELECT c.Bid ,c.Tid,c.Cid,count(*)"
					+ " FROM comments c, department d, users u "
					+ " WHERE d.Dep_name='" + input + "'"
					+ " AND u.Did=d.Dep_id"
					+ " AND u.Id=c.User_id"
					+ " GROUP BY c.Bid,c.Tid,c.Cid"
					+ " HAVING count(*)>=2";
			break;
		case "7":
			UserManager.printDepartment();
			System.out.print("Input Department name: ");
			input = sc.nextLine();
			sql = "SELECT CName, Description"
					+ " FROM CIRCLE"
					+ " WHERE Manager IN (  SELECT Id"
					+ "                    FROM USERS"
					+ "                    WHERE Did = (   SELECT Dep_id"
					+ "                                    FROM DEPARTMENT"
					+ "                                    WHERE Dep_name = '" + input + "') )";
			break;
		case "8":
			System.out.print("Input User ID: ");
			input = sc.nextLine();
			sql = "SELECT Title, Content"
					+ " FROM BOARD B"
					+ " WHERE Id IN (   SELECT Bid"
					+ "                FROM COMMENTS C"
					+ "                WHERE C.User_id = '" + input + "')";
			break;
		case "9":
			UserManager.printDepartment();
			System.out.print("Input Department ID: ");
			input = sc.nextLine();
			sql = "SELECT u.Id, u.name"
					+ " FROM USERS u"
					+ " WHERE NOT EXISTS (SELECT *"
					+ "    FROM BOARD b"
					+ "    WHERE b.User_id = u.Id"
					+ "    )"
					+ " AND u.did = " + input;
			break;
		case "10":
			showCategory();
			System.out.print("Input category ID: ");
			input = sc.nextLine();
			sql = "SELECT b.Title, circle.cname"
					+ " FROM BOARD b, CIRCLE"
					+ " WHERE NOT EXISTS (SELECT *"
					+ "    FROM COMMENTS c "
					+ "    WHERE c.Bid = b.Id AND b.Cid = c.Cid"
					+ "    )"
					+ " AND b.cid = CIRCLE.id"
					+ " AND circle.category_id = " + input;
			break;
		case "11":
			System.out.print("Select color(red / yellow / blue / purple / orange / green): ");
			input = sc.nextLine();
			sql = "Select u.Name "
					+ " FROM users u"
					+ " WHERE u.Id in "
					+ " (SELECT s.User_id"
					+ " FROM schedules s"
					+ " WHERE s.color='" + input + "')";
			break;
		case "12":
			UserManager.printDepartment();
			System.out.print("Input Department name: ");
			input = sc.nextLine();
			sql = "SELECT c.Cname"
					+ " FROM circle c"
					+ " WHERE c.id in "
					+ " (SELECT b.cid"
					+ " FROM belongs_to b"
					+ " WHERE b.user_id in "
					+ "            (SELECT u.id"
					+ "            FROM  users u, department d"
					+ "            WHERE u.Did=d.Dep_id AND d.Dep_name='" + input + "'))";
			break;
		case "13":
			System.out.print("Input minimum count of user: ");
			input = sc.nextLine();
			sql = "WITH SOMECIRCLES AS"
					+ "    (   SELECT Cid"
					+ "        FROM BELONGS_TO"
					+ "        GROUP BY Cid"
					+ "        HAVING COUNT (*) >= " + input + ")"
					+ " SELECT Cid, Cname"
					+ " FROM CIRCLE, SOMECIRCLES"
					+ " WHERE Id = Cid";
			break;
		case "14":
			System.out.print("Input minimum count of user: ");
			input = sc.nextLine();
			sql = "WITH SOMEDEPS AS"
					+ "    (   SELECT Did"
					+ "        FROM USERS"
					+ "        GROUP BY Did"
					+ "        HAVING COUNT (*) >= " + input + ")"
					+ " SELECT Dep_id, DEP_name"
					+ " FROM DEPARTMENT, SOMEDEPS"
					+ " WHERE Did = DEP_id";
			break;
		case "15":
			UserManager.printDepartment();
			System.out.print("Input Department name: ");
			input = sc.nextLine();
			sql = "SELECT c.Cname,c.Start_date, c.End_date"
					+ " FROM CIRCLE c ,USERS u, DEPARTMENT d, BELONGS_TO b"
					+ " WHERE d.Dep_name = '" + input + "' "
					+ " AND u.Did = d.Dep_id"
					+ " AND u.Id = b.User_id"
					+ " AND b.Cid = c.Id"
					+ " ORDER BY c.Start_date DESC";
			break;
		case "16":
			showCircle();
			System.out.print("Input Circle name: ");
			input = sc.nextLine();
			sql = "SELECT b.Title,b.Bdate"
					+ " FROM CIRCLE c, TAB_MENU t, BOARD b"
					+ " WHERE c.Cname LIKE '%" + input + "%'"
					+ " AND t.Cid = c.Id"
					+ " AND t.Cid = b.Cid AND t.Id = b.Tid"
					+ " ORDER BY Bdate DESC";
			break;
		case "17":
			System.out.print("Input Date(MMDD, example: 0716): ");
			input = sc.nextLine();
			sql = "SELECT count(*),did"
					+ " FROM users"
					+ " WHERE did in"
					+ " (SELECT d.dep_id"
					+ " FROM department d, circle c,users u"
					+ " WHERE '" + input + "'= TO_CHAR(c.start_date,'MMDD')"
					+ " AND c.manager=u.id"
					+ " AND d.dep_id=u.did)"
					+ " GROUP BY did"
					+ " ORDER BY did asc";
			break;
		case "18":
			System.out.print("Input range of people\nMin: ");
			input = sc.nextLine();
			System.out.print("Max: ");
			String input1 = sc.nextLine();
			sql = "SELECT color, count(*) "
					+ " FROM schedules"
					+ " WHERE user_id in ("
					+ " SELECT u.id"
					+ " FROM users u, circle c,belongs_to b"
					+ " WHERE c.max_person between " + input + " AND " + input1
					+ " AND b.user_id=u.id"
					+ " AND b.cid=c.id"
					+ " )"
					+ " GROUP BY color";
			break;
		case "19":
			showCircle();
			System.out.print("Input Circle id: ");
			input = sc.nextLine();
			sql = "SELECT Dep_id, Dep_name"
					+ " FROM DEPARTMENT"
					+ " MINUS"
					+ " SELECT Dep_id, Dep_name"
					+ " FROM USERS u, DEPARTMENT, BELONGS_TO"
					+ " WHERE Dep_id = Did"
					+ " AND u.id = USER_ID"
					+ " AND CID = " + input;
			break;
		case "20":
			System.out.print("Input count: ");
			input = sc.nextLine();
			sql = "SELECT CName"
					+ " FROM CIRCLE"
					+ " WHERE Max_person >= " + input
					+ " UNION"
					+ " SELECT DISTINCT CName"
					+ " FROM BOARD, CIRCLE C"
					+ " WHERE C.Id = Cid";
			break;
		case "21":
			return false;
		default:
			sql = "";
			break;
		}
		print(sql);
		return true;
	}
	
	private void showCategory() {
		DBHelper db = DBHelper.getInstance();
		
		ResultSet rs = db.runSql("SELECT * FROM CATEGORYS");
		System.out.println("ID\tNAME");
		try {
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	private void showCircle() {
		DBHelper db = DBHelper.getInstance();
		
		ResultSet rs = db.runSql("SELECT id, cname FROM CIRCLE");
		System.out.println("ID\tNAME");
		try {
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			return;
		}
	}
}
