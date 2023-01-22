import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables {

	static String url = "jdbc:sqlserver://localhost:1433;databaseName=NYAPI;encrypt=true;trustServerCertificate=true";
	static String user = "sa";
	static String pass = "root";

	public static void creatingArticalsTable() {

		String sql = "CREATE TABLE Articals (id INTEGER PRIMARY KEY IDENTITY(1,1)," + "title VARCHAR(255) NOT NULL,"
				+ "source VARCHAR(255) NOT NULL," + "published_date VARCHAR(255) NOT NULL)";

		Connection con = null;

		// Try block to check for exceptions
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate(sql);
			if (m <= 0)
				System.out.println("Created successfully : " + sql);
			else
				System.out.println("creating table failed");

			// Closing the connections
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void creatingAuthorsTable() {

		String sql = "CREATE TABLE Authors(id INTEGER PRIMARY KEY IDENTITY(1,1)," + "author VARCHAR(255) NOT NULL,"
				+ "contributor VARCHAR(255) NOT NULL," + "description VARCHAR(255) NOT NULL,"
				+ "title  VARCHAR(255) NOT NULL)";

		Connection con = null;

		// Try block to check for exceptions
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate(sql);
			if (m <= 0)
				System.out.println("Created successfully : " + sql);
			else
				System.out.println("creating table failed");

			// Closing the connections
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void creatingSectionsTable() {

		String sql = "CREATE TABLE Sections(id INTEGER PRIMARY KEY IDENTITY(1,1),"
				+ "section_name VARCHAR(255) NOT NULL," + "subsection_name VARCHAR(255) NOT NULL,"
				+ "document_type VARCHAR(255) NOT NULL)";

		Connection con = null;

		// Try block to check for exceptions
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			// Registering drivers
			DriverManager.registerDriver(driver);

			// Reference to connection interface
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			// Executing query
			int m = st.executeUpdate(sql);
			if (m <= 0)
				System.out.println("Created successfully : " + sql);
			else
				System.out.println("creating table failed");

			// Closing the connections
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
