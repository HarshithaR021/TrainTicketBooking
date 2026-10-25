package DataBaseDetails;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
	private static Connection con;
	static {
		try {
		 Class.forName(DbInfo.driver);
		 con=DriverManager.getConnection( DbInfo.url,DbInfo.userName,DbInfo.passwowrd);	
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		return con;
	}

}
