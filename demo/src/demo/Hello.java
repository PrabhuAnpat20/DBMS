

package demo;
import java.util.Scanner;
import java.sql.*;


public class Hello {
	  public static void main(String[] args) {
		  
		  Scanner sc=new Scanner(System.in);
		  String query,url,password,user;
		  Connection db=null;
		  ResultSet rs;
		  PreparedStatement ps;
		  url="jdbc:mysql://localhost:3306/java";
		  user="root";
		  password="1234";
		  String name;
		  int roll;
		  try {
			  Class.forName("com.mysql.jdbc.Driver");
			 db=DriverManager.getConnection(url,user,password);
			 Statement cStatement=db.createStatement();
			
			 if(db!=null && db.isValid(0)) {
				 System.out.println("connected successfully");
			 }
			
			  while(true) {
				  int ch=sc.nextInt();
				  if(ch==1) {
					  query="SELECT * FROM student";
			             rs=cStatement.executeQuery(query);
			             while(rs.next()) {
			            	 roll = rs.getInt(1);
			 				 name = rs.getString(2);
			 				int marks = rs.getInt(3);
			 				System.out.println(roll+"\t"+name+"\t"+marks+"\t");
			             }
				  }
				  if(ch==2) {
					  query="INSERT INTO student(roll,name,marks) VALUES (?,?,?)";
					  ps = db.prepareStatement(query);

	                    System.out.println("Enter roll: ");
	                    roll = sc.nextInt();
	                    sc.nextLine();
	                    System.out.println("Enter Name: ");
	                    name = sc.nextLine();
	                    System.out.println("Enter Marks: ");
	                    int marks = sc.nextInt();
	                    System.out.println();
	                    sc.nextLine(); // clears input buffer

	                    ps.setInt(1,roll);
	                    ps.setString(2, name);
	                    ps.setInt(3, marks);
	                   
	                    int rowsInserted = ps.executeUpdate();
	                    System.out.println(rowsInserted + " Record(s) Inserted Sucessfully");
				  }
			  }
			
             
			  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  
		  
	        System.out.println("Hello, World!");
	    }
}
