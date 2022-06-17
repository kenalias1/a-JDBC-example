package JDBCpackage;
public class main_app {

	public static void main(String[] args) {
		//Prepare connection and database
		String URL = "jdbc:mysql://localhost/";
		String username = "root";
		String password = "mysql";
		DB_storage.initiateDB(URL, username, password);		
		
		//database activity
		DB_storage.add_user();
		DB_storage.update_user_info();
		DB_storage.read_user_info();
		DB_storage.delete_user_info();
		
		//Now close the connection
		DB_storage.close_DB_connection();
		
	}

}
