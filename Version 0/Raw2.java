import java.sql.*;
public class Raw2
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/login";
	static final String USER = "root";
	static final String PASS = "password";
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to a seelected database");	
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully");
			System.out.println("Creating table in database");
			stmt = conn.createStatement();
			
//			String sql = "create table user(username varchar(15),password varchar(5))";
//			stmt.executeUpdate(sql);
			
//			String sql = "insert into user(username,password) values('hell','hel2')";
//			stmt.executeUpdate(sql);
//			System.out.println("Data inserted");
//			stmt.executeUpdate("insert into user(username,password) values('he','he2')");
			
//			String sql= "insert into user(username,password)values('achu','a123')"; //insert
//			stmt.executeUpdate(sql); //stmt interface has an execute method
//			System.out.println("Data inserted...");
//			String sql="UPDATE user SET username='bye' where password='hel2'";
//			stmt.executeUpdate(sql);
//			System.out.println("Data updated...");
		
//			String sql=" DELETE FROM user WHERE username='bye'";
//			stmt.executeUpdate(sql);
//			System.out.println("data deleted...");
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		
		}
	}
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//public class Raw1 {
//public static void main(String[] args) throws SQLException {
//	//Create Connection
//	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","password");
//	//Create statement/query
//	Statement stmt =con.createStatement();
//	String sql = "create table testdb(username varchar(15),password varchar(50))";
//	stmt.executeUpdate(sql);
//	String s = "INSERT INTO testdb VALUES(27, 'RAGHU')";
//	stmt.execute(s);
//	con.close();
//	System.out.println("Query Executed");
//	}
//}

