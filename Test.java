// Test.java
// Description: an app to demonstrate the manipulating data functions
// Author: Yongye Jin
// Date: 12/02/2018

package test;

//import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class Test {
 
    // JDBC 驱动名及数据库 URL
	// This will load the MySQL driver, each DB has its own driver
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://192.168.1.17:3306/events";
    static final String DB_URL = "jdbc:mysql://192.168.0.10:3306/student_records";
    
    // user name and password for the MySQL
    static final String USER = "cpsc440";
    static final String PASS = "ubuntu";
    
    public static void select_records() {
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
        
            // connect the database
            System.out.println("Connecting the database...");
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            // execute the search
            System.out.println("Executing the search...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, address, email FROM records";
            ResultSet rs = stmt.executeQuery(sql);
        
            // expand the table
            while(rs.next()){
                // search through the fields
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
    
                // output the data
                System.out.print("ID: " + id);
                System.out.print(", name: " + name);
                System.out.print(", address: " + address);
                System.out.print(", email: " + email);
                System.out.print("\n");
            }
            // close 
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // deal with JDBC errors
            se.printStackTrace();
        }catch(Exception e){
            // deal with Class.forName errors
            e.printStackTrace();
        }finally{
            // close resources
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// do nothing
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
    
    public static void insert_records() {
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
        
            // connect the database
            System.out.println("Connecting the database...");
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            // execute the search
            System.out.println("Executing the insert...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO records (id, name, address, email) "
            		+ "VALUES (NULL, \"Fred Freshen\", \"3876 Milton Ave, Irven, CA 92777\", \"fredf@qq.com\")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        
            System.out.println("Record insert has been submitted.");
            
            // close 
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // deal with JDBC errors
            se.printStackTrace();
        }catch(Exception e){
            // deal with Class.forName errors
            e.printStackTrace();
        }finally{
            // close resources
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// do nothing
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
    
   public static void delete_the_last_record() {
    	
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
        
            // connect the database
            System.out.println("Connecting the database...");
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            // execute the search
            System.out.println("Deleting the last record...");
            stmt = conn.createStatement();
            
            String sql;
            
            sql = "delete from records order by id desc limit 1";
            stmt.executeUpdate(sql);
            
        
            System.out.println("Record deleting has been submitted.");
            
            // close 
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // deal with JDBC errors
            se.printStackTrace();
        }catch(Exception e){
            // deal with Class.forName errors
            e.printStackTrace();
        }finally{
            // close resources
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// do nothing
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
       
 
    public static void main(String[] args) {
    
 	    // Declare the object and initialize with 
        // predefined standard input object 
        Scanner sc = new Scanner(System.in); 
        char ch;
        
    	while (true) {
    		
    		System.out.println("Data manipulating menu:");
    		System.out.println("1. Insert a record");
    		System.out.println("2. Display all records");
    		System.out.println("3. Delete the last record");
    		System.out.println("4. Modify the last record");
    		System.out.println("5. Quit");
    		System.out.println("Input your choice");
    		
            // Character input 
            ch = sc.next().charAt(0); 
 		
    		if (ch == '5') {
    		    //close(s);
    			break;
    		}
    		if (ch == '1') {
    			insert_records();
    		}
    		if (ch == '2') {
    			select_records();
    		}
    		if (ch == '3') {
    			delete_the_last_record();
    		}
    		if (ch == '4') {
    			System.out.println("Not done yet.");
    		}
    		
    		System.out.println("Input any char to continue....");
    		ch = sc.next().charAt(0);
    	}
    	
    	//insert_records();
    	//select_records();
    	//delete_the_last_record();
        //select_records();
    	System.out.println("Goodbye!");
    }
}

	
	