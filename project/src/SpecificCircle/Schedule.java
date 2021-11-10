package SpecificCircle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import ConsoleProject.DBHelper;
import ConsoleProject.User;

public class Schedule {
	
	static DBHelper db = DBHelper.getInstance();
	
	public User user;
	public int Cid;
	Scanner input;
	
	//static public ArrayList<String> colorList = new ArrayList<String>();

	public static HashMap<Integer, String>  color= new HashMap<Integer, String>(){
    	{
    		put(1,"red");
    		put(2,"blue");
    		put(3,"green");
    		put(4,"yellow");
    		put(5,"purple");
    		put(6,"oragne");
    	}
    	
    }; 
    

    
   

	Schedule(int cid,Scanner sc,User user){
		Cid=cid;
		input=sc;
		this.user=user;
	}
   
	
	
	
	

	public  void makeSchedule(String title, String content, int color,String startDate,String endDate) {
		
		int countS=getScheduleCount();
		String colorName=this.color.get(color);
		
	

		String sql="";
		
		sql="INSERT INTO SCHEDULES VALUES("+(countS+1)+",'"+user.getUserId()+"',"+Cid+",'"+colorName+"','"+content+"',"+"TO_DATE('"+startDate+"', 'yyyy-mm-dd'),"+"TO_DATE('"+endDate+"', 'yyyy-mm-dd'), '"+title+"')";
		
		System.out.print("정말로 스케쥴 을 등록하시겠습니까? 1:네 0:아니요(취소하기) :: ");
		if(input.nextInt()==1)
	    {
			System.out.println(sql);
			if(db.updateSql(sql)== -1)
			{	System.out.println("스케쥴을  등록하는 동안 오류가 발생 하였습니다.다시 시도해 주세요.");
				
			}
			else
				System.out.println("스케쥴이 성공적으로 등록되었습니다.");
	    	
	    }
		else
			return;
		
		
		
		
		
		

	}



	private int getScheduleCount() {
		// TODO Auto-generated method stub
	

		int count = 0;
		try {
		
			   String sql="";
			   sql="SELECT count(*) "+
			       "FROM SCHEDULES S "+
				   "WHERE S.cid="+Cid;
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            count = rs.getInt(1);
		          // System.out.println("스케줄  숫자:"+count);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return count;
		
	}
	
	
	private String getCurrentDate() {
		//System.out.println(now); 현재 시간 출력 
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formatedNow = formatter.format(now); // 포맷팅 적용
		return formatedNow;
		
	}
	
	
	

}
