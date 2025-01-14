package demo2;
import java.sql.*;
import java.util.Scanner;
public class app {
	
	public static void main(String[] args) {
	Connection db=null;
	String query,url,user,password;
	ResultSet rs;
	url="jdbc:mysql://localhost:3306/java";
	user="root";
	password="1234";
	int roll,marks;
	String name;
	Scanner sc=new Scanner(System.in);
	try {
		Class.forName("com.mysql.jdbc.Driver");
		db=DriverManager.getConnection(url,user,password);
		Statement st=db.createStatement();
		PreparedStatement ps;
		if(db!=null && db.isValid(0)) {
			System.out.println("Connected succesfully");
		}
		int ch=sc.nextInt();
		if(ch==1) {
			query="SELECT * FROM student";
			rs=st.executeQuery(query);
			while(rs.next()) {
				roll=rs.getInt(1);
				name=rs.getString(2);
				marks=rs.getInt(3);
				
				System.out.println("\t"+roll+"\t"+name+"\t"+marks);
				
			}
		}
		if(ch==2) {
			query="INSERT INTO student (roll,name,marks) VALUES (?,?,?);";
			ps=db.prepareStatement(query);
			  System.out.println("Enter roll: ");
              roll = sc.nextInt();
              sc.nextLine();
              System.out.println("Enter Name: ");
              name = sc.nextLine();
              System.out.println("Enter Marks: ");
              marks = sc.nextInt();
              System.out.println();
              sc.nextLine(); 

              ps.setInt(1,roll);
              ps.setString(2, name);
              ps.setInt(3, marks);
             
              int rowsInserted = ps.executeUpdate();
              System.out.println(rowsInserted + " Record(s) Inserted Sucessfully");
		}
		if (ch == 3) {
		    query = "UPDATE student SET marks = ? WHERE roll = ?";
		    ps = db.prepareStatement(query);

		    System.out.println("Enter roll to update: ");
		    roll = sc.nextInt();
		    sc.nextLine(); 

		    System.out.println("Enter new marks: ");
		    marks = sc.nextInt();
		    sc.nextLine(); 
		    ps.setInt(1, marks);
		    ps.setInt(2, roll);

		    int rowsUpdated = ps.executeUpdate();
		    System.out.println(rowsUpdated + " Record(s) Updated Successfully");
		}

	}
	catch(Exception e) {
		e.printStackTrace();
	}
		  

}
}
