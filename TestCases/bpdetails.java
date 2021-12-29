import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
public class bpdetails {
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
         con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/my", "root", "wicore123");

         System.out.println("Enter the first name: ");
    	 String firstname = s.nextLine();
    	 System.out.println("Enter the last name: ");
    	 String lastname = s.nextLine();
    	 pst = (PreparedStatement) con.prepareStatement("insert into bp(firstname,lastname) values(?,?)");

    	 pst.setString(1,firstname);
    	 pst.setString(2,lastname);
    	 int i = pst.executeUpdate();
         if(i!=0){
           System.out.println("added");
         }
         else{
           System.out.println("failed to add");
         }
    	 System.out.println("Connection is created successfully:");
    	 stmt = (Statement) con.createStatement();
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