package SpecificCircle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ConsoleProject.DBHelper;
import ConsoleProject.User;

public class crud {
	
	static DBHelper db = DBHelper.getInstance();
	
	public String circleName;
	public User user;
	public Tab currentTab;
	private int Cid;
	Scanner input;


	public crud(int cid, User user,Scanner sc) {
		this.Cid = cid;
		this.user=user;
		this.input = sc;
		this.circleName = getCircleName();
		//Cid=getCid();
		// TODO Auto-generated constructor stub
	}
	
	public int returnCid() {
		return Cid;
	}


	public void makePost(String title, String contents, Scanner input) {
		
		
		//int cid=getCid();
		int tid=getTid();
		int countB=0;
		countB=getBoardcount(tid);     //getBoardCount는 해당 tab의 총 게시물 갯수를 return. 

		String sql="";
		
		sql="INSERT INTO BOARD VALUES("+(countB+1)+",TO_DATE('"+getCurrentDate()+"', 'yyyy/mm/dd HH24:MI'),'"+title+"','"+contents+"',"+tid+","+Cid+",'"+user.getUserId()+"')";
		
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
	
	
	   public void searchPost() {

		      // int Cid=getCid();
		      int Tid = getTid();
		      int Bid = 0;
		      String comment = "";
		      Scanner input = new Scanner(System.in);

		      try {

		         String sql = "";
		         sql = "SELECT ID,BDATE,TITLE,CONTENT,USER_ID " + "FROM BOARD B " + "WHERE B.tid=" + Tid + " AND B.cid="
		               + Cid + "ORDER BY BDATE DESC";

		         ResultSet rs = db.runSql(sql);

		         while (rs.next()) {
		            int id = rs.getInt(1);
		            String date = rs.getString(2);
		            String title = rs.getString(3);
		            String content = rs.getString(4);
		            String userId = rs.getString(5);

		            System.out.println("-----------------------------------");
		            System.out.println("번호 :" + id); // 보드 아이
		            System.out.println("제목 " + title);
		            System.out.println("내용 " + content);
		            System.out.println("작성일 " + date);
		            System.out.println("작성자 " + userId);
		            System.out.println("-------------------------------------");

		            // System.out.println(cname+":"+cid);

		         }

		         rs.close();
		      } catch (SQLException ex2) {
		         System.err.println("sql error= " + ex2.getMessage());
		         System.exit(1);
		      }

		      System.out.print("댓글을 달고싶은경우 1 댓글을 보고싶은경우 2나가고 싶을 경우 0 을 입력해주세요: ");
		      int choice = input.nextInt();
		      if (choice == 0)
		         return;
		      if (choice == 1) {
		         System.out.println("댓글을 작성하고 싶은 게시물의 번호를 적어주세요");

		         Bid = Integer.parseInt(input.next());

		         System.out.print(Bid + "번 게시글에 남길 댓글을 작성해주세요(한줄만 허용 ): ");

		         input.nextLine();

		         comment = input.nextLine();
		         System.out.print("'" + comment + "'" + "\n위 내용을 정말로 남기겠습니까? 1:네 0:아니요 ::");
		         if (input.nextInt() == 0) {
		            return;
		         }

		         makeComment(Bid, Cid, Tid, user, comment);
		      }
		      if (choice == 2) {
		         System.out.println("댓글을 보고 싶은 게시물의 번호를 적어주세요");

		         Bid = Integer.parseInt(input.next());
		         System.out.println("");
		         showComment(Bid, Cid, getTid());
		      }

		   }

		   public void showComment(int Bid,int Cid, int Tid) {
		      String sql = "select * from comments where bid=" + Bid + " and cid =" + Cid + " and Tid =" + Tid;
		      
		      ResultSet rs = db.runSql(sql);

		      Date cDate;
		      String content;
		      String userId;
		      try {
		         while (rs.next()) {
		            cDate = rs.getDate(2);
		            content = rs.getString(3);
		            userId = rs.getString(7);
		            System.out.println(userId +" " +cDate.toString());
		            System.out.println("> "+ content);
		            System.out.println("--------------------------");

		         }
		         rs.close();
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }

		      
		      
		      
		   }
	
	
	  public  void modifyPost(Scanner input) {
		// TODO Auto-generated method stub
		//chekAuthority(user2);
	
		int Bid=0;
		//int Cid=getCid();
		int Tid=getTid();
		String sql="";
		
		
		System.out.println("---------------내가 작성한 게시글------------- ");
        ShowPost(Tid,user);
        System.out.println("---------------여기까지 입니다.------------- ");
      
    
        while(true){
        	System.out.println("1: 게시글 수정 \n2: 게시글 삭제 \n3: 나가기");
        	String value=input.next();
        	 switch(value) {
             case "1":
            	 System.out.print("수정하고 싶은 게시물의 번호: ");
         		 Bid=Integer.parseInt(input.next());
         		System.out.print("내용을 입력하세요 <엔터+exit>을 하게되 종료됩니다. (title은 변경 할 수 없음): ");
 			   try
 			      {
 			          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 			          StringBuffer sb=new StringBuffer();
 			          String str = "";
 			        

 			          while((str = br.readLine()) != null)
 			          {
 			        	  if (str.contentEquals("exit"))
 			        		  break;
 			              //System.out.println(str);
 			              sb.append(str);
 			              
 			          }
 			      System.out.println("내용: "+sb.toString());
 		          updatePost(1,sb.toString(),Tid,Bid,user);  //1이면 update
 			          

 			      }
 			      catch (Exception e)
 			      {
 			          e.printStackTrace();
 			      }
         	
             	break;
             case "2":
            	 System.out.print("삭제하고 싶은 게시물의 번호: ");
         		 Bid=Integer.parseInt(input.next());
         		 updatePost(0,null,Tid,Bid,user);  //0이면 delete
             	
         		 
             	break;
             case "3":
            	 System.out.println("----페이지에서 나가는 중입니다.---- ");
             	break;
            	
             }
        	 if (value.equals("3"))
        		 break;
        		
        	 
   
        }
     
        
		
	}
	
	public void updatePost(int flag,String content,int Tid,int Bid,User user) {   //게시글 수정 ,삭제 
		
	// System.out.println("게시글 수정 ");
		String sql="";
		
		if (flag==1)   // 수정  
		{
			sql ="UPDATE BOARD SET content='"+content+"' where user_id='"+user.getUserId()+"' and tid="+Tid+" AND cid="+Cid+" AND id="+Bid;
			System.out.println(sql + Bid);
     		if(db.updateSql(sql)== -1)
			{	System.out.println("게시글을  수정하는 동안 오류가 발생 하였습니다.다시 시도해 주세요.");
				
			}
			else
				System.out.println("-----게시글이 성공적으로 수정 되었습니다.------");
		}
		else if (flag==0)  // 삭제 
		{
			sql="DELETE FROM BOARD where user_id='"+user.getUserId()+"' and tid="+Tid+" AND cid="+Cid+" AND id="+Bid;
			System.out.println(sql);
		       if(db.updateSql(sql)== -1)
				{	System.out.println("게시글을  삭제하는  동안 오류가 발생 하였습니다.다시 시도해 주세요.");
					
				}
				else
					System.out.println("-----게시글이 성공적으로 수정 되었습니다.------");
		}
		
       
		
	}
	
 

	public String getCircleName() {
		String cName = null;
		try {
			   String sql="";
			   sql="SELECT C.Cname "+
			       "FROM CIRCLE C "+
				   "WHERE C.id = "+String.valueOf(Cid);
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            cName = rs.getString(1);
		          //  System.out.println(cname+":"+Cid);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		
		return cName;
	}
	

	public int getCid() {
		int cid = 0;
		try {
			   String sql="";
			   sql="SELECT C.id "+
			       "FROM CIRCLE C "+
				   "WHERE C.cname LIKE '%"+circleName+"%'";
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            cid = rs.getInt(1);
		          //  System.out.println(cname+":"+Cid);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return cid; 		
	}
	
	public int getTid() {
		int tid = 0;
	
		try {
			

			   String sql="";
			   sql="SELECT T.id "+
			       "FROM TAB_MENU T "+
				   "WHERE T.tname LIKE '%"+currentTab.tabname+"%' AND T.cid="+Cid;
			   	   
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
	
	public int getBoardcount(int Tid) {
		
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
	
	public int getCommentcount(int Bid,int Tid)
	{
		
		int count = 0;
		try {
		
			   String sql="";
			   sql="SELECT count(*) "+
			       "FROM COMMENTS C "+
				   "WHERE C.tid="+Tid+" AND C.cid="+Cid+" AND C.bid="+Bid;
			   	   
			   ResultSet rs = db.runSql(sql);
		  
		         while(rs.next()) {  
		            count = rs.getInt(1);
		           System.out.println("총 댓글 수 :"+count);
		           
		         }
		       
		         rs.close();
			   }catch(SQLException ex2) {
				   System.err.println("sql error= "+ex2.getMessage());
				   System.exit(1);}
		return count;
		
		
	}

	
	public void makeComment(int Bid, int Cid,int Tid,User user,String comment) {
		
		
	   int countC=getCommentcount(Bid,Tid);
	   
	  
		
		String sql="";
		sql="INSERT INTO COMMENTS VALUES("+(countC+1)+",TO_DATE('"+getCurrentDate()+"', 'yyyy/mm/dd HH24:MI'),'"+comment+"',"+Bid+","+Tid+","+Cid+",'"+user.getUserId()+"')";
		//System.out.println(sql);
		if(db.updateSql(sql)== -1)
		{	System.out.println("댓글을  등록하는 동안 오류가 발생 하였습니다.다시 시도해 주세요.");
			
		}
		else
			System.out.println("댓글이 성공적으로 등록되었습니다.");
		
		
	}
	
	public void ShowPost(int tid,User user) {
		
			
			
			try {
				   String sql="";
				   sql="SELECT id,title,content,bdate "+
				       "FROM BOARD b "+
					   "WHERE user_id LIKE '%"+user.getUserId()+"%' AND tid="+tid+"AND cid="+Cid;
				 
				   ResultSet rs = db.runSql(sql);
					  
			         while(rs.next()) {  
			            int id = rs.getInt(1);
			            String title=rs.getString(2);
			            String content=rs.getString(3);
			            String bdate=rs.getString(4);
			               
			            System.out.println("-----------------------------------");
			            System.out.println("번호 :"+id);   //게시글 아이디 
			            System.out.println("제목 "+title);
			            System.out.println("내용 "+content);
			            System.out.println("작성일 "+bdate);
			            System.out.println("-------------------------------------");
			       
				   }
			         rs.close();
			}catch(SQLException ex2) {
					   System.err.println("sql error= "+ex2.getMessage());
					   System.exit(1);}
			
		}

	
	private void chekAuthority(User user2) {
		// TODO Auto-generated method stub
		
		System.out.println("권한이 있습니다.");
		
	}
	private String getCurrentDate() {
		//System.out.println(now); 현재 시간 출력 
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String formatedNow = formatter.format(now); // 포맷팅 적용
		return formatedNow;
		
	}
	
}
