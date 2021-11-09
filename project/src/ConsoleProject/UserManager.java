package ConsoleProject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	private boolean checkInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public User getUserInfoByConsole(Scanner sc) {
		String id, pwd, name, email, did, birth_year, birth_month, birth_day;
		System.out.println("Input Your ID: ");
		id = sc.nextLine();
		System.out.println("Input Your Password: ");
		pwd = sc.nextLine();
		System.out.println("Input Your Name: ");
		name = sc.nextLine();
		System.out.println("Input Your Email: ");
		email = sc.nextLine();
		int d_size = printDepartment();
		while(true) {
			System.out.println("Input Your Department ID: ");
			did = sc.nextLine();
			if(checkInt(did) && Integer.parseInt(did) > 0 && Integer.parseInt(did) <= d_size) break;
			else System.out.println("Input Valid Number");
		}
		
		System.out.println("Input Your Birth Date");
		while(true) {
			System.out.println("Year: ");
			birth_year = sc.nextLine();
			if(checkInt(birth_year)) {
				break;
			}
			else System.out.println("Input Numeric Data");
		}
		while(true) {
			System.out.println("Month: ");
			birth_month = sc.nextLine();
			if(checkInt(birth_month)) break;
			else System.out.println("Input Numeric Data");
		}
		while(true) {
			System.out.println("day: ");
			birth_day = sc.nextLine();
			if(checkInt(birth_day)) break;
			else System.out.println("Input Numeric Data");
		}
		
		User user = new User(id, pwd, name, email, did, Date.valueOf(birth_year + "-" + birth_month + "-" + birth_day));
		
		return user;
	}
	
	public User signInByConsole(Scanner sc) {
		DBHelper db = DBHelper.getInstance();
		
		System.out.println("Input ID: ");
		String id = sc.nextLine();
		System.out.println("Input Password: ");
		String pwd = sc.nextLine();
		
		String sql = "SELECT * "
				+ "FROM USERS "
				+ "WHERE id = '" + id+ "' "
				+ "AND pwd = '" + pwd + "'";
		
		ResultSet rs = db.runSql(sql);
		User user = null;
		try {
			if(rs.next())
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public int addUser(User user) {
		DBHelper db = DBHelper.getInstance();
		
		String sql = "INSERT INTO USERS VALUES('"
				+ user.getUserId() + "', '"
				+ user.getUserPwd() +  "', '"
				+ user.getUserName() + "', '"
				+ user.getUserEmail() + "', "
				+ user.getUserDid() + ", TO_DATE('"
				+ user.getUserBirthDate() + "', 'yyyy/mm/dd'))";
		
		return db.updateSql(sql);
	}
	
	public int updateUser(User user) {
		DBHelper db = DBHelper.getInstance();
		
		String sql = "UPDATE USERS SET NAME = '"
				+ user.getUserName() + "', EMAIL = '"
				+ user.getUserEmail() + "', DID = "
				+ user.getUserDid() + ", BIRTH_DATE = TO_DATE('"
				+ user.getUserBirthDate() + "', 'yyyy/mm/dd')"
				+ " WHERE ID = '" + user.getUserId() + "'";
		
		return db.updateSql(sql);
	}
	
	public int changePwd(User user) {
		DBHelper db = DBHelper.getInstance();
		
		String sql = "UPDATE USERS SET PWD = '"
				+ user.getUserPwd() + "'"
				+ " WHERE ID = '" + user.getUserId() + "'";
		
		return db.updateSql(sql);
	}
	
	public int deleteUser(User user) {
		DBHelper db = DBHelper.getInstance();
		String sql = "DELETE FROM USERS WHERE ID = '" + user.getUserId() + "'";
		return db.updateSql(sql);
	}
	
	private int printDepartment() {
		DBHelper db = DBHelper.getInstance();
		
		ResultSet rs = db.runSql("SELECT * FROM DEPARTMENT");
		ArrayList<Department> d = new ArrayList<>();
		try {
			while(rs.next()) {
				Department temp = new Department(rs.getInt(1), rs.getString(2));
				d.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < d.size() / 4; i++) {
			System.out.println(String.format("%3d.%-19s", d.get(i).getDid(), d.get(i).getName())
				+ "\t" + String.format("%3d.%-15s", d.get(i + (d.size() / 4)).getDid(), d.get(i + (d.size() / 4)).getName())
				+ "\t" + String.format("%3d.%-15s", d.get(i + (d.size() * 2 / 4)).getDid(), d.get(i + (d.size() * 2 / 4)).getName())
				+ "\t" + String.format("%3d.%-15s", d.get(i + (d.size() * 3 / 4)).getDid(), d.get(i + (d.size() * 3 / 4)).getName()));
		}
		return d.size();
	}
	
	public int userConfig(Scanner sc, User user) {
		String input, email, birth_year, birth_month, birth_day, did;
		while(true) {
			System.out.println("1.Update user data\t2.Change password\t3.Delete User\t4.Back");
			input = sc.nextLine();
			if(input.equals("1")) {
				System.out.println("Input Your Email: ");
				email = sc.nextLine();
				int d_size = printDepartment();
				while(true) {
					System.out.println("Input Your Department ID: ");
					did = sc.nextLine();
					if(checkInt(did) && Integer.parseInt(did) > 0 && Integer.parseInt(did) <= d_size) break;
					else System.out.println("Input Valid Number");
				}
				
				System.out.println("Input Your Birth Date");
				while(true) {
					System.out.println("Year: ");
					birth_year = sc.nextLine();
					if(checkInt(birth_year)) {
						break;
					}
					else System.out.println("Input Numeric Data");
				}
				while(true) {
					System.out.println("Month: ");
					birth_month = sc.nextLine();
					if(checkInt(birth_month)) break;
					else System.out.println("Input Numeric Data");
				}
				while(true) {
					System.out.println("day: ");
					birth_day = sc.nextLine();
					if(checkInt(birth_day)) break;
					else System.out.println("Input Numeric Data");
				}
				user.setUserDid(did);
				user.setUserEmail(email);
				user.setUserBirth_date(Date.valueOf(birth_year + "-" + birth_month + "-" + birth_day));
				updateUser(user);
				return 0;
			}
			else if(input.equals("2")) {
				System.out.println("Input new password: ");
				user.setUserPwd(sc.nextLine());
				if(changePwd(user) == 1)
					System.out.println("Changed Password");
			}
			else if(input.equals("3")) {
				System.out.println("Do you want to delete user " + user.getUserId() + "? (y/n)");
				input = sc.nextLine();
				if(input.equals("y") || input.equals("Y")) {
					deleteUser(user);
					return 1;
				}
			}
			else if(input.equals("4")) {
				return 0;
			}
		}
	}
}
