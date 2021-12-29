
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
public class Javaempdetails {
   public static void main(String[] args) {
      Connection con = null;
      Statement stmt = null;
      PreparedStatement pst = null;
      Scanner s = new Scanner(System.in);
      try {
         try {
        	 Class.forName("com.mysql.jdbc.Driver").newInstance();
         } catch (Exception e) {
            System.out.println(e);
         }
         con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/empdet", "root", "wicore123");

         System.out.println("Enter the first name: ");
    	 String firstname = s.nextLine();
    	 System.out.println("Enter the last name: ");
    	 String lastname = s.nextLine();
    	 System.out.println("Enter the gender: ");
    	 String gender = s.nextLine();
    	 System.out.println("Enter the phone number: ");
    	 String phonenumber = s.nextLine();
    	 System.out.println("Enter the email: ");
    	 String email = s.nextLine();
    	 System.out.println("Enter the state: ");
    	 String state = s.nextLine();
    	 System.out.println("Enter the Address: ");
    	 String Address = s.nextLine();
    	 System.out.println("Enter the Employee designation: ");
    	 String Employeedesignation = s.nextLine();
    	 
         pst = (PreparedStatement) con.prepareStatement("insert into empdetails(firstname,lastname,gender,phonenumber,email,state,Address,Employeedesignation) values(?,?,?,?,?,?,?,?)");

    	 pst.setString(1,firstname);
    	 pst.setString(2,lastname);
    	 pst.setString(3,gender);
    	 pst.setString(4,phonenumber);
    	 pst.setString(5,email);
    	 pst.setString(6,state);
    	 pst.setString(7,Address);
    	 pst.setString(8,Employeedesignation);
    	 int i = pst.executeUpdate();
         if(i!=0){
           System.out.println("added");
         }
         else{
           System.out.println("failed to add");
         }
    	 System.out.println("Connection is created successfully:");
      stmt = (Statement) con.createStatement();
      /*String query1 = "INSERT INTO empdetails " + "VALUES ('Brahmini', 'Boina', 'Female', 9398378567, 'bhanuboina7@gmail.com', 'AndhraPradesh', 'Visakhapatnam','Java Developer')";
      stmt.executeUpdate(query1);
      query1 = "INSERT INTO empdetails " + "VALUES ('Bhanu', 'prasad', 'male', 9978568567, 'bhanuprasad7@gmail.com', 'Telangana', 'Hyderabad','Full Stack Developer')";
      stmt.executeUpdate(query1);
      query1 = "INSERT INTO empdetails " + "VALUES ('Kavya', 'K', 'Female', 9273423567, 'kavyak1@gmail.com', 'Karnataka', 'Bangalore','Testing Engineer')";
      stmt.executeUpdate(query1);
       query2 = "INSERT INTO empdetails " + "VALUES ('Nandini', 'M', 'Female', 9845643567, 'nandinim8@gmail.com', 'TamilNadu', 'Chennai','UI Designer')";
      stmt.executeUpdate(query2);
      query1 = "INSERT INTO empdetails " + "VALUES ('Sandeep', 'Banoth Kumar', 'male', 9450988767, 'sandeepbanoth4@gmail.com', 'Kerala', 'Trivendrum','UX Developer')";
      stmt.executeUpdate(query1);*/
      System.out.println("Record is inserted in the table successfully..................");
      } catch (SQLException excep) {
         excep.printStackTrace();
      } catch (Exception excep) {
         excep.printStackTrace();
      } finally {
         try {
            if (stmt != null)
               con.close();
         } catch (SQLException se) {}
         try {
            if (con != null)
               con.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }  
      }
      System.out.println("Please check it in the MySQL Table......... ……..");
   }
}