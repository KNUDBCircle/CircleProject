package SpecificCircle;


public class Tab {
	String tabname;
	int number;

	public Tab(String string, int i) {
		tabname=string;
		number=i;
	
		// TODO Auto-generated constructor stub
	}
	
	public void  show_info() {

	    System.out.println("(number) : " + tabname);
	    System.out.println("(tab name) : " + number);
		
		
	}
	
	public String toString() {
		return String.format("%d번 %s", number,tabname);    
	}
	
}
