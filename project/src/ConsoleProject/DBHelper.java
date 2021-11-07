package ConsoleProject;
import java.sql.*;
public class DBHelper {
	   private static DBHelper instance = new DBHelper();
	   
	   public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	   public static final String USER_ID ="circle";
	   public static final String USER_PASSWD ="circle";
	   
	   private Connection conn = null; // Connection object
	   private Statement stmt = null;   // Statement object
	   
	   public DBHelper() {
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         System.out.println("Success");
	      } catch (ClassNotFoundException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	         System.exit(1);
	      }
	      
	      try {
	         conn = DriverManager.getConnection(URL, USER_ID, USER_PASSWD);
	         conn.setAutoCommit(false);
	         stmt = conn.createStatement();
	         System.out.println("connected");
	      } catch (SQLException e1) {
	         // TODO Auto-generated catch block
	         System.out.println("Cannot get a connection: " + e1.getLocalizedMessage());
	         System.out.println("Cannot get a connection: " + e1.getMessage());
	         System.exit(1);
	      }
	   }
	   
	   public static DBHelper getInstance() { return instance; }
	   
	   public int updateSql(String sql) {
	      try {
	         int res = stmt.executeUpdate(sql);
	         conn.commit();
	         return res;
	      } catch (SQLException e) {
	         System.out.println("error: " + e.getMessage());
	         return -1;
	      }
	   }
	   
	   public ResultSet runSql(String sql) {
	      try {
	         return stmt.executeQuery(sql);
	      } catch (SQLException e) {
	         System.out.println("error: " + e.getMessage());
	         return null;
	      }
	   }
	}