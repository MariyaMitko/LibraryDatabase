package by.mitsko.library.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionHelper {
	
	private static final String DB_CONNECT_PROPERTY = "db_config";

	private DBConnectionHelper(){
		
	}

	public static Connection connect(){
		Connection connection = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle(DB_CONNECT_PROPERTY);
			String url = rb.getString("db.url");
			String login = rb.getString("db.login");
			String pass = rb.getString("db.pass");
			
			Class.forName(rb.getString("db.driver"));
			
			connection = DriverManager.getConnection(url, login, pass);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	public static void disconnect (Connection connection){
		if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
}
