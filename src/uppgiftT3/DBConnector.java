package uppgiftT3;
import java.sql.*;

public class DBConnector 
{
	
	public static Connection getConnection()  {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		String url = "jdbc:sqlserver://localhost:1433;database=StudentRegister;";
		String user = "rasmus";
		String pass = "Grupp8";
		try {
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected");
			return con;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String findStudent(String studentSsn, int column) {

		try {
			Connection con = getConnection();
			String query = "SELECT * FROM Student WHERE studentSsn = " + studentSsn +";";
			PreparedStatement pr = con.prepareStatement(query);
			ResultSet rs = pr.executeQuery();
			
			while (rs.next()) {
				return rs.getString(column);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

public static void addStudent (String ssn, String name, String address, String phone) {
	Connection con = getConnection();
	
		try {
			String ssn1 = findStudent(ssn, 1);
			if (ssn1==null) {
			
			PreparedStatement ps = con.prepareStatement ("INSERT INTO Student " +
														 "VALUES (?,?,?,?)");
			
			ps.setString(1, ssn );
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, phone);
			
			ps.executeUpdate();
			}	
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}	
	
}

public static String removeStudent(String ssn) {
	
	Connection con = getConnection();
	String ssn1 = findStudent(ssn, 1);
	if (ssn1!=null) {
		try {
		String query = "DELETE FROM Student WHERE studentSsn = " + ssn1 +";";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs.getString(1);
	
	}
	catch (SQLException e) {
		e.getStackTrace();
	}
	catch (Exception ex) {		
	}
	}
	return null;
}

public static String findCourse (String courseCode) {
	Connection con = getConnection();
	try {
		String query = "SELECT * FROM Course WHERE courseCode =" + courseCode + ";";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (rs!=null){
			return rs.getString(1);
		}
	}catch (SQLException e) {
		e.getStackTrace();
	}
	catch (Exception ex) {		
	}	
	return null;
}



public static String findStudentCourse (String ssn, String courseCode) {
	Connection con = getConnection();
	try {
		String query = "SELECT * FROM Student WHERE studentSsn = " + ssn + " AND courseCode =" + courseCode + ";";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			return ssn;
		}
	}
		catch (SQLException e) {
			e.getStackTrace();
		}
		catch (Exception ex) {		
		}
		return null;
}

public static String removeStudentCourse (String studentSsn, String courseCode) {
	Connection con = getConnection();
	String ssn = findStudentCourse(studentSsn,courseCode);{
		if (ssn!=null) {
			try {
				String query = "DELETE * FROM Studies WHERE studentSsn = "+ studentSsn + "AND courseCode= " +courseCode +";";
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					return rs.getString(1);
				}
			}
			
			catch (SQLException e) {
				e.getStackTrace();
			}
			catch (Exception ex) {		
			}			
		}return null;

	}
}

public static String addStudentCourse (String studentSsn, String courseCode) {
		Connection con = getConnection();
		String snr = findStudent(studentSsn, 1);
		if (snr!=null) {
			try {
				String query = "INSERT INTO Studies VALUES (" + studentSsn + "," + courseCode + ");";
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					return rs.getString(1);
				}
			}
			
			catch (SQLException e) {
				e.getStackTrace();
			}
			catch (Exception ex) {		
			}			
		}return null;
	}






}