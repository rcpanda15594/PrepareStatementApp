//Page80------PreparedStatement Example

package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PstInsertTest {
private static final String query="INSERT INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) throws Exception {
		
		Scanner scn=null;
		int list=0;
		Connection con=null;		
		PreparedStatement ps=null;
		int no=0;
		String name=null;
		int fee=0;
		int sdetails=0;
try {			
	//read inputs
	scn=new Scanner(System.in);
	System.out.println("Enter Student List:");
	if(scn!=null) {
		list=scn.nextInt();
	}//if
	
		
	//register jdbc driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
			
//establish connection
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
	
//prepare SQL Query
	
	if(con!=null) {
		ps=con.prepareStatement(query);
	}
	
//read multiple sets of inputs from enduser
	if(scn!=null) {
		for(int i=1;i<=list;i++) {
			System.out.println("Enter "+i+"Student Details");
			System.out.println("Enter Student No:");
			no=scn.nextInt();
			scn.nextLine();
			System.out.println("Enter Student Name:");
			name=scn.next();
			scn.nextLine();
			System.out.println("Enter Student Fees");
			fee=scn.nextInt();
			
//set the inputs values read from enduser to query params
			ps.setInt(1,no);
			ps.setString(2,name);
			ps.setInt(3,fee);
			
			
	//execute Query;
			sdetails=ps.executeUpdate();
			if(sdetails==0) {
				System.out.println(i+"Student details are not inserted");
			}
				else {
					System.out.println(i+"Student details are inserted");
					
				}//else
			
		}//for
		
	}//if
	
	}//try

catch(Exception e) {
	e.printStackTrace();
	
}//catch

finally {
	
//close jdbc objects
	
	
	
    try {
		
		if(con!=null)
			con.close();
		
	}catch(Exception se) {
		se.printStackTrace();
	}//catch
	
	
     try {
		
		if(scn!=null)
			scn.close();
		
	}catch(Exception se) {
		se.printStackTrace();
	}//catch
     
}//finally

		
		

	}//main

}//class
