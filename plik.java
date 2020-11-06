import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class plik {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://Full202086308:3306/KKalan";

   static final String USER = "kkalan";
   static final String PASS = "kkalan1";
   static Scanner in = new Scanner( System.in);
   static Connection conn = null;
   static Statement stmt = null;
   static String option;
   static int Id;
   static String newId;
   static String firstName;
   static String lastName;
   static String phoneNumber;
   static ResultSet rs;
   static String sql;

	
   public static void displayNumbers()
   {   
	try{
	sql = "SELECT ID, FirstName, LastName, PhoneNumber FROM ContactBook";
	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	while(rs.next()){
	  	 Id  = rs.getInt("ID");
		 firstName = rs.getString("FirstName");
		 lastName = rs.getString("LastName");
		 phoneNumber = rs.getString("PhoneNumber");

		 System.out.println(Id+"    "+firstName+"    "+lastName+"    "+phoneNumber+"\n");
	} rs.close();
	}catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	
   }

   public static void addNumber()
   {   
	try{
	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	stmt = conn.createStatement();
		
	System.out.println("ID:");
	newId = in.nextLine();

        System.out.println("Enter first name:");
        firstName = in.nextLine();

        System.out.println("Enter last name:");
        lastName = in.nextLine();

        System.out.println("Enter phone number:");
        phoneNumber = in.nextLine();

        sql = " INSERT INTO ContactBook (ID, FirstName, LastName, PhoneNumber) VALUES ('"+newId+"', '"+firstName+"', '"+lastName+"', '"+phoneNumber+"')";
        System.out.println("sql:"+sql);
        stmt.executeUpdate(sql);
	}catch(SQLException se){
	      se.printStackTrace();
	   }
   }

   public static void editNumber()
   {	   
	try{
	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	stmt = conn.createStatement();
		
        System.out.println("Enter ID of number that you want to edit:");
        newId = in.nextLine();

        System.out.println("Enter first name:");
        firstName = in.nextLine();

        System.out.println("Enter last name:");
        lastName = in.nextLine();

        System.out.println("Enter phone number:");
	phoneNumber = in.nextLine();

        sql = " UPDATE ContactBook SET FirstName = '"+firstName+"' , LastName = '"+lastName+"', PhoneNumber = '"+phoneNumber+"' WHERE ID= '"+newId+"';";
        stmt.executeUpdate(sql);
	}catch(SQLException se){
	      se.printStackTrace();
	   }
   }

   public static void deleteNumber()
   {   
       try{
       conn = DriverManager.getConnection(DB_URL,USER,PASS);
       stmt = conn.createStatement();
	       
       System.out.println("Enter ID of number that you want to delete");
       newId = in.nextLine();
       sql = " DELETE FROM ContactBook WHERE ID= '"+newId+"';";
       stmt.executeUpdate(sql);
	}catch(SQLException se){
	      se.printStackTrace();
	   }
   }

   public static void main(String[] args) {
   
   try{
      TimeUnit.SECONDS.sleep(10);
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to database...");

      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      
      sql = "CREATE TABLE IF NOT EXISTS ContactBook (ID int, FirstName varchar(255), LastName varchar(255), PhoneNumber varchar(255) );";
      stmt.executeUpdate(sql); //stworzenie tabeli
      

      do{
	 System.out.println("1. Add new number \n2. Display all numbers\n3. Edit number\n4. Delete number\n5. Exit application");
         option = in.nextLine();
	
	 if(option.equals("1"))
	 {
	    addNumber();
	 }
	 else if(option.equals("2"))
	 {
	    displayNumbers();
	 }
 	 else if(option.equals("3"))
	 {
	    editNumber();
	 }
	 else if(option.equals("4"))
	 {
	    deleteNumber();
	 }
	 else
	 {
	    System.out.println("You choosed wrong option.");
	 }
         
      }while (!option.equals("5"));

      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      e.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
 }
}
