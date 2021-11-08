package SpecificCircle;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ConsoleProject.DBHelper;
import ConsoleProject.User;

public class crud {
	
	static DBHelper db = DBHelper.getInstance();
	Date now = new Date();
	SimpleDateFormat format1;
	SimpleDateFormat format4;
	
	

	public void makePost(String title, String contents, String cname,String tname,User user) {
		
		
		int cid=getCid(cname);
		int tid=getTid(tname,cid);
		int countB=0;
		countB=getBoardcount(cid,tid)+1;
		Scanner input=new Scanner(System.in);
	
		//System.out.println(now); 현재 시간 출력 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String formatedNow = formatter.format(now); // 포맷팅 적용
		
		
		String sql="";
		
		sql="INSERT INTO BOARD VALUES("+countB+",TO_DATE('"+formatedNow+"', 'yyyy/mm/dd HH24:MI'),'"+title+"','"+contents+"',"+tid+","+cid+",'"+user.getUserId()+"')";
		
		System.out.print("정말로 글을 등록하시겠습니까? 1:네 0:아니요(취소하기) :: ");
		if(input.nextInt()==1)
	    {
			System.out.println(sql);
			if(db.updateSql(sql)== -1)
			{	System.out.println("게시글을  등록하는 동안 오류가 발생 하였습니다.다시 시도해 주세요.");
				
			}
			else
				System.out.println("게시글이 성공적으로 등록되었습니다.");
	    	
	    }
		else
			return;
	 	
	}
	
	
	public void searchPost(String cname, String tname){
		
		
		int Cid=getCid(cname);
		int Tid=getTid(tname,Cid);
		Scanner input=new Scanner(System.in);
	
		try {
			
			String sql="";
			sql="SELECT ID,BDATE,TITLE,CONTENT,USER_ID "+
				       "FROM BOARD B "+
					   "WHERE B.tid="+Tid+" AND B.cid="+Cid+
					  "ORDER BY BDATE DESC";
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            int id = rs.getInt(1);
		            String date=rs.getString(2);
		            String title=rs.getString(3);
		            String content=rs.getString(4);
		            String userId=rs.getString(5);
		            
		            System.out.println("-----------------------------------");
		            System.out.println("번호 :"+id);
		            System.out.println("제목 "+title);
		            System.out.println("내용 "+content);
		            System.out.println("작성일 "+date);
		            System.out.println("작성자 "+userId);
		            System.out.println("-------------------------------------");
		            
		          //  System.out.println(cname+":"+cid);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		
			System.out.print("댓글을 달고 싶은 경우 1 나가고 싶을 경우 0 을 입력해주세요: ");
			if(input.nextInt()==0)
				return;
			
			System.out.print("댓글을 작성해주세요(한줄): ");
			String comment=input.nextLine();
			System.out.print(comment+"\n위 내용을 정말로 남기겠습니까? 1:네 0:아니요 ::");
			if (input.nextInt()==0)
			{
				return;
			}
			
			//댓글등
			
//			String sql2="";
//			sql2="INSERT INTO BOARD VALUES("+countB+",TO_DATE('"+formatedNow+"', 'yyyy/mm/dd HH24:MI'),'"+title+"','"+contents+"',"+cid+","+tid+",'"+user.getUserId()+"')";
//			//System.out.println(sql);
//			if(db.updateSql(sql)== -1)
//			{	System.out.println("댓글을  등록하는 동안 오류가 발생 하였습니다.다시 시도해 주세요.");
//				
//			}
//			System.out.printl("댓글이 성공적으로 등록되었습니다.");
			
	
		
	}
	
	
	
	public int getCid(String cname) {
		int cid = 0;
		try {
			

			   String sql="";
			   sql="SELECT C.id "+
			       "FROM CIRCLE C "+
				   "WHERE C.cname LIKE '%"+cname+"%'";
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            cid = rs.getInt(1);
		          //  System.out.println(cname+":"+cid);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return cid; 		
	}
	
	public int getTid(String tname,int cid) {
		int tid = 0;
		try {
			

			   String sql="";
			   sql="SELECT T.id "+
			       "FROM TAB_MENU T "+
				   "WHERE T.tname LIKE '%"+tname+"%' AND T.cid="+cid;
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            tid = rs.getInt(1);
		           // System.out.println(tname+":"+tid);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return tid; 		
	}	
	
	public int getBoardcount(int Cid,int Tid) {
		
		int count = 0;
		try {
		
			   String sql="";
			   sql="SELECT count(*) "+
			       "FROM BOARD B "+
				   "WHERE B.tid="+Tid+" AND B.cid="+Cid;
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            count = rs.getInt(1);
		           System.out.println("게시물 숫자:"+count);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return count;
		
		
	}
	
}
