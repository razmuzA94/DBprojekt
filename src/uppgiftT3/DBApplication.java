package uppgiftT3;

import java.sql.SQLException;
public class DBApplication {
	
	public static void main(String [] args) throws SQLException{
		String ssn = "222";
		String name ="peter";
		String address = "ada";
		String phone = "000";
		DBConnector.addStudent(ssn,name,address,phone);
		
		}
			
	
	}

