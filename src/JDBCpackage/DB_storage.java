package JDBCpackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB_storage {
	
    static Connection conn;    
    
//INITIATE MYSQL SERVER CONNECTION AND CREATE A NEW DATABASE..................................................
	static int initiateDB(String URL, String username, String password) {
		Statement stmt;
		String sql;
		
		try { Class.forName("com.mysql.cj.jdbc.Driver");}
		catch (ClassNotFoundException cnfe) {
		    System.out.println("Can't load MySQL driver, exiting...");
		    System.exit(-1);
		}		
		
		try {				
		    conn = DriverManager.getConnection(URL, username, password);
		    stmt = conn.createStatement();
		 
		    if (conn != null) {	  	        
		       
		        sql = "DROP DATABASE IF EXISTS USERDB_11111";
		        stmt.execute(sql);		        
		        
		        sql = "CREATE DATABASE USERDB_11111";
		        stmt.execute(sql);
		        System.out.println("Database USERDB created successfully..."); 
		        
		        sql = "USE USERDB_11111";
		        stmt.execute(sql);
		        System.out.println("Use databse USERDB_11111...");  	
		        
		        sql = "CREATE TABLE users(\r\n"
		        		+ "    user_id int NOT NULL AUTO_INCREMENT,\r\n"
		        		+ "    username varchar(45) NOT NULL,\r\n"
		        		+ "    password varchar(45) NOT NULL,\r\n"		        
		        		+ "    email varchar(45) NOT NULL,\r\n"
		        		+ "    PRIMARY KEY (user_id)\r\n"
		        		+ ");";
		        stmt.execute(sql);
		        System.out.println("Create table users...");  
		        System.out.println("....................................................................."); 
		    }
		    
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		}
		return 1;
	}
	
	//ADD A NEW USER.........................................................................
	static int add_user() {
		PreparedStatement stmt;
	    String sql;
		sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "TranLuong");
		stmt.setString(2, "mypassword");		
		stmt.setString(3, "luongtran@example.com");
		 
		int rowsInserted = stmt.executeUpdate();
		if (rowsInserted > 0) {
		    System.out.println("Add a new user...");
		}
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		};			
		
		return 1;
	}
	
	//UPDATE USER INFO.........................................................................
	static int update_user_info() {
		
		String sql = "UPDATE users SET password=?, email=? WHERE username=?";		 
		
		try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "anewpassword");				
		stmt.setString(2, "luongtran@example2.com");
		stmt.setString(3, "TranLuong");
		 
		int rowsUpdated = stmt.executeUpdate();
		if (rowsUpdated > 0) {
		    System.out.println("Update a new user's info...");
		}
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		};			
		
		return 1;
	}
	
	
	//READ USER INFO.........................................................................
	static int read_user_info() {
		
		String sql = "SELECT * FROM users WHERE username=?";		 
		
		try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "TranLuong");
		 
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int id = rs.getInt(1);
		String username = rs.getString(2);
		String pass = rs.getString(3);
		String email = rs.getString(4);
		
		System.out.printf("Read user info: ID: %d	username: %s	password: %s	email: %s\n", id, username, pass, email);		
		
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		};			
		
		return 1;
	}
	
	
	//READ USER INFO.........................................................................
	static int delete_user_info() {
		
		String sql = "DELETE FROM users WHERE username=?";		 
		
		try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "TranLuong");
		 
		 stmt.executeUpdate();		
		
		System.out.println("Delete a user...");		
		
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		};			
		
		return 1;
	}
	static int close_DB_connection() {
		try {
		conn.close();
		System.out.println("Database server connection closed.");
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    System.exit(-1);
		}
		return 1;
	}
	


}
