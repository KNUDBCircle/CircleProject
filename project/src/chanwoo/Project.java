package chanwoo;
import java.sql.*;

public class Project{
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_COMPANY ="COMPANY";
	public static final String USER_PASSWD ="COMPANY";
	
	public static void main(String[] args) {
	      Connection conn = null; // Connection object
	      Statement stmt = null;   // Statement object
	      try {
	          Class.forName("oracle.jdbc.driver.OracleDriver");
	          System.out.println("DB Driver Success!");
	       }catch(ClassNotFoundException e){
	          System.out.println("error = " + e.getMessage());
	          System.exit(1);         
	       }
	       try {
	          conn = DriverManager.getConnection(URL, USER_COMPANY, USER_PASSWD);
	          System.out.println("DB Connected.");
	          stmt = conn.createStatement();
	       }catch(SQLException ex) {
	          ex.printStackTrace();
	          System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
	          System.err.println("Cannot get a connection: " + ex.getMessage());
	          System.exit(1);
	       }
	       
	       //로그인 정보 저장
	       
	       User user = new User("hso0473", "배찬우");
	       
	       while()
	       

	      
	      
	      
	      
	      try {
	          // Close the Statement object.
	          stmt.close(); 
	          // Close the Connection object.
	          conn.close();
	       } catch (SQLException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	       }
	   }

}
