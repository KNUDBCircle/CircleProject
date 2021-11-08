package SpecificCircle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ConsoleProject.User;

public class enterCircle extends crud {
	
//	 static DBHelper db = DBHelper.getInstance();
	
	 public String circle_name;
	 public User user;
	 
	 
	 public static ArrayList<Tab> tab_info= new ArrayList<Tab>();//타입설정 Student객체만 사용가능
	 public Tab currentTab;
	 
	 
	public enterCircle(String name,User user ){
		circle_name=name;
		this.user=user;	
	}
	

	public void printMenu() {
	
		int num;
		System.out.println("Welcome "+circle_name );
		while(true)
		{	
//			System.out.println("Welcome "+circle_name );
			System.out.println("----------Please select tab menu(현재 동아리:"+circle_name+")------ ");
			getTname();    //circle 이름으로 동아리에 속한 tab 정보 가져오기 
			Scanner input= new Scanner(System.in);
			
			System.out.println("0번 뒤로가기 ");
			for (Tab i :tab_info) {
				System.out.println(i);
			}
		    
		
			System.out.println("번호::  ");	
			num=input.nextInt();	
			
			if (num==0)
			{
				System.out.println("정말로 "+ circle_name+"을 나가시겠습니까?  1:예 0:아니요 :: ");
				if(input.nextInt()==1)
					return;
				else {
					
					System.out.println("----------Please select tab menu(현재 동아리:"+circle_name+")------ ");
					for (Tab i :tab_info) {
						System.out.println(i);
					}
					System.out.println("번호::  ");	
					num=input.nextInt();
					
				}	
			}
		
			currentTab=getCurrentTab(num);   //현재 위치하고있는 탭 메뉴
			System.out.println(currentTab+"으로 이동하였습니다.");
			
			if (checkManager(user)&&(currentTab.tabname).equals("TAB 추가하기"))
			{
				System.out.print("Tab 이름: ");
			    String tabTitle=input.next();
			    System.out.println("정말로 "+ tabTitle+"을 추가하시겠습니?  1:예 0:아니요 :: ");
				if(input.nextInt()==1)
					addTab(tabTitle);
			        
			}

			else {
				run(); 
				
			}
			 
		
		}
	}
	
	  
	      
	      
   private void run() {
	   int num;  //메뉴번호 저장 
	   while(true) {
		   Scanner input=new Scanner(System.in);
		   System.out.println("게시물 올리기(1) 게시물 조회하기(2) 게시물 수정하기(3) 게시물 삭제하기(4) 뒤로가기(5)");
		   System.out.println("-----숫자를 입력해주세요---->");
		   
		   num=input.nextInt();
		   
		   switch(num)
		   {
		   case 1: //게시물 올리기 
			   System.out.print("제목을  입력하세요 : ");
			   String title=input.nextLine();
			   title=input.nextLine();
			   System.out.print("내용을 입력하세요 <엔터+exit>을 하게되 종료됩니다. : ");
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
			      System.out.println("제목: "+title+"  내용: "+sb.toString());
		          makePost(title, sb.toString(),circle_name,currentTab.tabname,user);
			          

			      }
			      catch (Exception e)
			      {
			          e.printStackTrace();
			      }
			   break;
			   
		   case 2:  //게시물 조회하기 
			  searchPost(circle_name,currentTab.tabname);
			  break;
			   
			 
			       
		   case 3:  //게시물 수정하기 
			   
			   
		   case 4:  //게시물 삭제하기 
			   
			   
		   case 5:  //뒤로가기  
			   break;
	
		   }
		   if (num==5)
			   break;
		   
	   }
	   System.out.println(currentTab.tabname+"을 나가는 중입니다.");
	   
		
	}




   public  void getTname() {
	   
	   int count=1;
	 
	   try {

	   String sql="";
	
		sql="SELECT tname "+
		   	   "FROM tab_menu t, circle c " +
		   	   "WHERE c.cname LIKE '%"+circle_name+"%' AND c.id=t.cid ";
 
	
	   ResultSet rs = db.runSql(sql);
	   
	   tab_info.clear();  //탭 비워주기 
         while(rs.next()) {  
            String fname = rs.getString(1);
            
            //System.out.println(count+" :  " + fname );
            tab_info.add(new Tab(fname,count));   //탭 정보에 추가하
            count++;
         }
       
         rs.close();
         
	   }catch(SQLException ex2) {
		   System.err.println("sql error= "+ex2.getMessage());
		   System.exit(1);} 
	   
	   if (checkManager(user)) 
       {
		   tab_info.add(new Tab("TAB 추가하기",count));
	   	  }
		
	   
	 

   }


   
   public Tab getCurrentTab(int num) {
	   for (Tab i :tab_info) {
			if (i.number==num)
				return i;
		}
	  return null; 
	   
   }
   
   public boolean checkManager(User u) {
	   
	   String manager="";
	   
	   try {

		   String sql="";
			sql="SELECT manager "+
			   	   "FROM circle c " +
			   	   "WHERE c.cname LIKE '%"+circle_name+"%' ";
	 
		
		   ResultSet rs = db.runSql(sql);
 
	         while(rs.next()) {  
	             manager = rs.getString(1);
	            // System.out.println(manager);     
	         }
	         rs.close();
		   }catch(SQLException ex2) {
			   System.err.println("sql error= "+ex2.getMessage());
			   System.exit(1);} 
				 
	   if (manager.equalsIgnoreCase(user.getUserId())) {
		   //System.out.println("you are manager.(탭을 추가하실 수 있습니다.)");
		
		   return true;
	   }
	   
	   return false;
	   
	   
   }
   
   
   public void addTab(String tabTitle) {
	   
	   int count=0;
	   String sql="";
	   int cid=getCid(circle_name);
	   
	   try {
		   
			sql="SELECT count(*) "+
			   	   "FROM TAB_MENU ";
	 
		   ResultSet rs = db.runSql(sql);
		   while(rs.next()) { 
	             count = rs.getInt(1);
         }
	         rs.close();
		   }catch(SQLException ex2) {
			   System.err.println("sql error= "+ex2.getMessage());
			   System.exit(1);} 
	   
	
	  
	   
		sql="INSERT INTO TAB_MENU VALUES("+(count+1)+","+cid+",'"+tabTitle+"')";
		System.out.println(sql);
		if(db.updateSql(sql)!= -1)
		{	
			System.out.println("tab menu가  성공적으로 등록되었습니다.");
		}
	
	   
	 
   
   

  }
   
	   
   
   
   
	   
	  
		   
		 
	      
	   
}

