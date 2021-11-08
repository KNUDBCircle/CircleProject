package chanwoo;
import ConsoleProject.DBHelper;
import java.sql.*;

public class Category {	
	public static void showCategory(DBHelper db) {

		ResultSet rs = null;
		String sql = "select * from categorys";
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

	public static String getCategoryName(DBHelper db, int categoryId) {
		ResultSet rs;
		String sql = "select Cvalue from categorys where id =  " + String.valueOf(categoryId); 
		rs = db.runSql(sql);
		try {
			rs.next();
			String temp = rs.getString(1);
			rs.close();
			return temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
